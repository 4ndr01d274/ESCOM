og10Third([_,_,N|_], R):- log10(N,R).

ordenAsc([]).
ordenAsc([_]).
ordenAsc([X,Y|R]):-X=<Y, ordenAsc([Y|R]).

numpr(X,Z):-(Y is (X**10)), (Z is (Y**(1/3))).


producto([X],X).
producto([X|Y],P):-producto(Y,M), P is M*X.

tam(L1,L2,P):- length(L1,LeL1),length(L2,LeL2),
    (LeL1>=LeL2,producto(L1,P);LeL1<LeL2,producto(L2,P)).

cuboyraiz(A,B,C,D):-(A>B, C is A**(1/2), D is B**3);(A<B, C is B**(1/2), D is A**3).

cuantas([A|B],C,D):-(cuantas(B,C,X), A==C, D is X+1);(cuantas(B,C,X), A\==C,D is X).
cuantas([],_,0).


