from kb import KB, Boolean, Integer

# Initialise all,ariables that you need for you strategies and game knowledge.
# Add those,ariables here.. The following list is complete for the Play Jack strategy.
A0 = Boolean('a0')
A1 = Boolean('a1')
A2 = Boolean('a2')
A3 = Boolean('a3')
A4 = Boolean('a4')
A5 = Boolean('a5')
A6 = Boolean('a6')
A7 = Boolean('a7')
A8 = Boolean('a8')
A9 = Boolean('a9')
A10 = Boolean('a10')
A11 = Boolean('a11')
A12 = Boolean('a12')
A13 = Boolean('a13')
A14 = Boolean('a14')
A15 = Boolean('a15')
A16 = Boolean('a16')
A17 = Boolean('a17')
A18 = Boolean('a18')
A19 = Boolean('a19')
T0 = Boolean('t0')
T1 = Boolean('t1')
T2 = Boolean('t2')
T3 = Boolean('t3')
T4 = Boolean('t4')
T5 = Boolean('t5')
T6 = Boolean('t6')
T7 = Boolean('t7')
T8 = Boolean('t8')
T9 = Boolean('t9')
T10 = Boolean('t10')
T11 = Boolean('t11')
T12 = Boolean('t12')
T13 = Boolean('t13')
T14 = Boolean('t14')
T15 = Boolean('t15')
T16 = Boolean('t16')
T17 = Boolean('t17')
T18 = Boolean('t18')
T19 = Boolean('t19')
PE0 = Boolean('pe0')
PE1 = Boolean('pe1')
PE2 = Boolean('pe2')
PE3 = Boolean('pe3')
PE4 = Boolean('pe4')
PE5 = Boolean('pe5')
PE6 = Boolean('pe6')
PE7 = Boolean('pe7')
PE8 = Boolean('pe8')
PE9 = Boolean('pe9')
PE10 = Boolean('pe10')
PE11 = Boolean('pe11')
PE12 = Boolean('pe12')
PE13 = Boolean('pe13')
PE14 = Boolean('pe14')
PE15 = Boolean('pe15')
PE16 = Boolean('pe16')
PE17 = Boolean('pe17')
PE18 = Boolean('pe18')
PE19 = Boolean('pe19')




def general_information(kb):
    # GENERAL INFORMATION ABOUT THE CARDS
    # This adds information which cards are Jacks
    kb.add_clause(A0)
    kb.add_clause(A5)
    kb.add_clause(A10)
    kb.add_clause(A15)
    kb.add_clause(T1)
    kb.add_clause(T6)
    kb.add_clause(T11)
    kb.add_clause(T16)

    # Add here what,er is needed for your strategy.

def strategy_knowledge(kb):
    # DEFINITION OF THE STRATEGY
    # Add clauses (This list is sufficient for this strategy)
    # PJ is the strategy to play jacks firs, so all we need to model is all x PJ(x) <~> J(x,
    # In other words that the PJ strategy should play a card when it is a jack
    # pk(x) <~> k(x) & q(x)
    kb.add_clause(~A0, PE0)
    kb.add_clause(~A5, PE5)
    kb.add_clause(~A10, PE10)
    kb.add_clause(~A15, PE15)
    kb.add_clause(~PE0, A0)
    kb.add_clause(~PE5, A5)
    kb.add_clause(~PE10, A10)
    kb.add_clause(~PE15, A15)

    kb.add_clause(~T1, PE1)
    kb.add_clause(~T6, PE6)
    kb.add_clause(~T11, PE11)
    kb.add_clause(~T16, PE16)
    kb.add_clause(~PE1, T1)
    kb.add_clause(~PE6, T6)
    kb.add_clause(~PE11, T11)
    kb.add_clause(~PE16, T16)


