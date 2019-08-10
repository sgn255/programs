#!/usr/bin/env python
"""
This is a bot that applies propositional logic reasoning to determine its strategy.
The strategy it uses is determined by what is defined in load.py. Here it is to always
pick a Jack to play whenever this is a legal move.

It loads general information about the game, as well as the definition of a strategy,
from load.py.
"""

from api import State, util, Deck
import random, marry, cheap, expen


from kb import KB, Boolean, Integer

class Bot:
    num_samples = -1
    # How deep to sample
    depth = -1
    def __init__(self, num_samples=4, depth=8):
        self.__num_samples = num_samples
        self.__depth = depth

    def get_move(self, state):
        moves = state.moves()
        trump = state.get_trump_suit()
        random.shuffle(moves)
        score = [11, 10, 4, 3, 2]
        played_card = state.get_opponents_played_card()

        if state.get_phase()==1:
            if played_card is None:
                for move in moves:
                    if move[0] is None:
                        return move;
                for move in moves:
                    if Deck.get_suit(move[0]) == trump:
                        if not self.marry_kb_consistent(state, move, moves):
                            return move
                for move in moves:
                    if not self.marry_kb_consistent(state, move, moves):
                        return move
                for move in moves:
                    if not self.cheap_kb_consistent(state, move, moves):
                        return move
                for move in moves:
                    if not self.expen_kb_consistent(state, move, moves):
                        return move
                return random.choice(moves)
            else:
                higherCards=[]
                for move in moves:
                    if move[0] is not None and Deck.get_suit(move[0]) != state.get_trump_suit():
                        if (score[move[0] % 5] > score[played_card % 5]) and Deck.get_suit(played_card) != state.get_trump_suit() and \
                                Deck.get_suit(move[0])==Deck.get_suit(played_card):
                            higherCards.append(move)
                if len(higherCards)>0:
                    smallest=20
                    for i in higherCards:
                        if score[i[0]%5]<smallest:
                            higherCards[0]=i
                            smallest = score[i[0]%5]
                if len(higherCards)>0:
                    return higherCards[0]

                for move in moves:
                    if move[0] is not None and Deck.get_suit(move[0]) == state.get_trump_suit():
                        if score[move[0] % 5] > score[played_card % 5]:
                            return move

                for move in moves:
                    if move[0] is not None and score[played_card % 5]<=score[3] and Deck.get_suit(played_card) != state.get_trump_suit():
                        if Deck.get_suit(move[0]) != state.get_trump_suit() and score[move[0] % 5] <= score[3]:
                            return move

                for move in moves:
                    if move[0] is not None and Deck.get_suit(move[0]) == state.get_trump_suit():
                        if Deck.get_suit(played_card) == state.get_trump_suit() and  score[move[0] % 5] > score[played_card %5]:
                            return move
                return random.choice(moves)

        else:
            imp = [0, 1, 5, 6, 10, 11, 15, 16]
            scores = [11, 10, 4, 3, 2]

            played_card = state.get_opponents_played_card()
            if played_card is not None:
                for move in moves:
                    if ((move[0] % 5) < (played_card % 5)) & (Deck.get_suit(move[0]) == Deck.get_suit(played_card)):
                        return move
                for move in moves:
                    if ((move[0] % 5) < (played_card % 5)) & (state.get_trump_suit() == Deck.get_suit(move[0])):
                        return move
                return random.choice(moves)
            else:
                for move in moves:
                    if move[0] % 5 < 1 & (trump != Deck.get_suit(move[0])):
                        return move
                for move in moves:
                    if move[0] % 5 < 2 & (trump != Deck.get_suit(move[0])):
                        return move
                for move in moves:

                    if move[0] % 5 < 2 & (trump == Deck.get_suit(move[0])):
                        return move
                for move in moves:

                    if move[0] % 5 >= 2 & (trump != Deck.get_suit(move[0])):
                        return move

                return random.choice(moves)

    def evaluate(self, state, player):
        score = 0.0

        for _ in range(self.__num_samples):
            st = state.clone()

                # Do some random moves
            for i in range(self.__depth):
                if st.finished():
                    break

                st = st.next(random.choice(st.moves()))
            score += self.heuristic(st, player)
        return score / float(self.__num_samples)

    def heuristic(self, state, player):
        return util.ratio_points(state, player)


    # Note: In this example, the state object is not used,
    # but you might want to do it for your own strategy.

    def cheap_kb_consistent(self, state, move,moves,):
    # type: (State, move) -> bool
        kb = KB()
        cheap.general_information(kb)
        cheap.strategy_knowledge(kb)
        index = move[0]
        variable_string = "pc" + str(index)
        strategy_variable = Boolean(variable_string)
        kb.add_clause(~strategy_variable)
        return kb.satisfiable()

    def expen_kb_consistent(self, state, move, moves, ):
        # type: (State, move) -> bool
        kb = KB()
        expen.general_information(kb)
        expen.strategy_knowledge(kb)
        index = move[0]
        variable_string = "pe" + str(index)
        strategy_variable = Boolean(variable_string)
        kb.add_clause(~strategy_variable)
        return kb.satisfiable()


    def marry_kb_consistent(self, state, move,moves,):
    # type: (State, move) -> bool
        kb = KB()
        marry.general_information(kb)
        marry.strategy_knowledge(kb)
        index = move[0]
        variable_string = "pk" + str(index)
        for mov in moves:
            ind = mov[0]
            card = Boolean("q"+str(ind))
            kb.add_clause(card)
        strategy_variable = Boolean(variable_string)
        kb.add_clause(~strategy_variable)
        return kb.satisfiable()


    # if i go first:
    # jack swap
    # play trump wedding
    # play wedding
    # play cheap card non trump
    # play high non trump

    # if i go second:
    # play next highest non trump card
    # play very high trump card
    # play lowest non trump if there card is king or lower non trump
    # if there card is trump, try and beat it
    # play lowest card

    # for round 2 change to mybot or rdeep

