"""
RandomBot -- A simple strategy: enumerates all legal moves, and picks one
uniformly at random.
"""

# Import the API objects
from api import State, util, Deck
import random


class Bot:

    def __init__(self):
        pass

    def get_move(self, state):
        # type: (State) -> tuple[int, int]
        """
        Function that gets called every turn. This is where to implement the strategies.
        Be sure to make a legal move. Illegal moves, like giving an index of a card you
        don't own or proposing an illegal mariage, will lose you the game.
       	TODO: add some more explanation
        :param State state: An object representing the gamestate. This includes a link to
            the states of all the cards, the trick and the points.
        :return: A tuple of integers or a tuple of an integer and None,
            indicating a move; the first indicates the card played in the trick, the second a
            potential spouse.
        """

        # All legal moves
        ''' moves = state.moves()
        imp=[0,1,5,6,10,11,15,16]
        high=[]

        played_card = state.get_opponents_played_card()
        if played_card is not None:
            for move in moves:
                if (move[0] in imp) & (move[0] < played_card):
                    return move
            return max(moves)
        else:
            for move in moves:
                if move[0] in imp:
                    high.append(move)
            if len(high)>0:
                return min(high)
            else:
                return max(moves)'''
        moves = state.moves()
        imp = [0, 1, 5, 6, 10, 11, 15, 16]
        scores =[11,10,4,3,2]
        high = []

        played_card = state.get_opponents_played_card()
        if played_card is not None:
            for move in moves:
                if ((move[0]%5) < (played_card % 5)) & (util.get_suit(move[0])==util.get_suit(played_card)):
                    return move
            for move in moves:

                if ((move[0]%5) < (played_card % 5)) & (state.get_trump_suit()==util.get_suit(move[0])):
                    return move
            return max(moves)
        else:
            for move in moves:
                if move[0]%5 < 1 & (state.get_trump_suit()!=util.get_suit(move[0])):
                    return move
            for move in moves:
                if move[0]%5 < 2 & (state.get_trump_suit()!=util.get_suit(move[0])):
                    return move
            for move in moves:

                if move[0]%5 < 2 & (state.get_trump_suit()==util.get_suit(move[0])):
                    return move
            for move in moves:

                if move[0]%5 >= 2 & (state.get_trump_suit()!=util.get_suit(move[0])):
                    return move

                return max(moves)


