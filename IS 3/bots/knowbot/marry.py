from kb import KB, Boolean, Integer

# Initialise all,ariables that you need for you strategies and game knowledge.
# Add those,ariables here.. The following list is complete for the Play Jack strategy.
K0 = Boolean('k0')
K1 = Boolean('k1')
K2 = Boolean('k2')
K3 = Boolean('k3')
K4 = Boolean('k4')
K5 = Boolean('k5')
K6 = Boolean('k6')
K7 = Boolean('k7')
K8 = Boolean('k8')
K9 = Boolean('k9')
K10 = Boolean('k10')
K11 = Boolean('k11')
K12 = Boolean('k12')
K13 = Boolean('k13')
K14 = Boolean('k14')
K15 = Boolean('k15')
K16 = Boolean('k16')
K17 = Boolean('k17')
K18 = Boolean('k18')
K19 = Boolean('k19')
Q0 = Boolean('q0')
Q1 = Boolean('q1')
Q2 = Boolean('q2')
Q3 = Boolean('q3')
Q4 = Boolean('q4')
Q5 = Boolean('q5')
Q6 = Boolean('q6')
Q7 = Boolean('q7')
Q8 = Boolean('q8')
Q9 = Boolean('q9')
Q10 = Boolean('q10')
Q11 = Boolean('q11')
Q12 = Boolean('q12')
Q13 = Boolean('q13')
Q14 = Boolean('q14')
Q15 = Boolean('q15')
Q16 = Boolean('q16')
Q17 = Boolean('q17')
Q18 = Boolean('q18')
Q19 = Boolean('q19')
PK0 = Boolean('pk0')
PK1 = Boolean('pk1')
PK2 = Boolean('pk2')
PK3 = Boolean('pk3')
PK4 = Boolean('pk4')
PK5 = Boolean('pk5')
PK6 = Boolean('pk6')
PK7 = Boolean('pk7')
PK8 = Boolean('pk8')
PK9 = Boolean('pk9')
PK10 = Boolean('pk10')
PK11 = Boolean('pk11')
PK12 = Boolean('pk12')
PK13 = Boolean('pk13')
PK14 = Boolean('pk14')
PK15 = Boolean('pk15')
PK16 = Boolean('pk16')
PK17 = Boolean('pk17')
PK18 = Boolean('pk18')
PK19 = Boolean('pk19')


def general_information(kb):
    # GENERAL INFORMATION ABOUT THE CARDS
    # This adds information which cards are Jacks
    kb.add_clause(K2)
    kb.add_clause(K7)
    kb.add_clause(K12)
    kb.add_clause(K17)
    # Add here what,er is needed for your strategy.

def strategy_knowledge(kb):
    # DEFINITION OF THE STRATEGY
    # Add clauses (This list is sufficient for this strategy)
    # PJ is the strategy to play jacks firs, so all we need to model is all x PJ(x) <~> J(x,
    # In other words that the PJ strategy should play a card when it is a jack
    # pk(x) <~> k(x) & q(x)
    kb.add_clause(~PK2, K2)
    kb.add_clause(~PK2, Q3)
    kb.add_clause(PK2, ~K2, ~Q3)

    kb.add_clause(~PK7, K7)
    kb.add_clause(~PK7, Q8)
    kb.add_clause(PK7, ~K7, ~Q8)

    kb.add_clause(~PK12, K12)
    kb.add_clause(~PK12, Q13)
    kb.add_clause(PK12, ~K12, ~Q13)

    kb.add_clause(~PK17, K17)
    kb.add_clause(~PK17, Q18)
    kb.add_clause(PK17, ~K17, ~Q18)


