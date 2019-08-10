"""
Train a machine learning model for the classifier bot. We create a player, and watch it play games against itself.
Every observed state is converted to a feature vector and labeled with the eventual outcome
(-1.0: player 2 won, 1.0: player 1 won)

This is part of the second worksheet.
"""
from api import State, util

# This package contains various machine learning algorithms
import sys
import sklearn
import sklearn.linear_model
import sklearn.naive_bayes
import sklearn.neural_network
from sklearn.externals import joblib
from sklearn.model_selection import cross_val_predict

import matplotlib.pyplot as plt

from scipy.stats import linregress
from bots.rand import rand
from bots.knowbot import knowbot
# from bots.rdeep import rdeep
from bots.rdeep import rdeep
from bots.rand import rand

from bots.mltest.mltest import features

# How many games to play
GAMES = 10000

# Which phase the game starts in
PHASE = 1

# The player we'll observe
#player = rdeep.Bot()
player = rand.Bot()

data = []
target = []
test=[]
for g in range(GAMES):

    # Randomly generate a state object starting in specified phase.
    state = State.generate(phase=PHASE)

    state_vectors = []

    while not state.finished():

        # Give the state a signature if in phase 1, obscuring information that a player shouldn't see.
        given_state = state.clone(signature=state.whose_turn()) if state.get_phase() == 1 else state

        # Add the features representation of a state to the state_vectors array
        state_vectors.append(features(given_state))

        # Advance to the next state
        move = player.get_move(given_state)
        state = state.next(move)

    winner, score = state.winner()

    for state_vector in state_vectors:
        data.append(state_vector)

        if winner == 1:
            result = 'won'
            test.append(1)

        elif winner == 2:
            result = 'lost'
            test.append(2)

        target.append(result)

    sys.stdout.write(".")
    sys.stdout.flush()
    if g % (GAMES/10) == 0:
        print("")
        print('game {} finished ({}%)'.format(g, (g/float(GAMES)*100)))

# Train a logistic regression model
learner = sklearn.linear_model.LogisticRegression()
#learner = sklearn.naive_bayes.GaussianNB()
#learner = sklearn.neural_network.MLPClassifier()
model = learner.fit(data, target)

print len(data[:, 1])
print len(test)




# Check for class imbalance
count = {}
for str in target:
    if str not in count:
        count[str] = 0
    count[str] += 1

print('instances per class: {}'.format(count))

# Store the model in the ml directory
joblib.dump(model, './bots/mltest/model.pkl')

print('Done')
