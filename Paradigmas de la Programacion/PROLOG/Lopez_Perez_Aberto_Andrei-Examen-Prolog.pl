%Base de Conocimiento Ejercicio 1

%Ejercicio 1
cospow(X,Y):-Z is X**4,cos(Z,Y).

%Ejercicio2

sumatoria([X],X).
sumatoria([X|Y],P):-sumatoria(Y,M), P is M+X.

lmax(L1,L2,X):-sumatoria(L1,Z), sumatoria(L2,Y),(Z>=Y, append(L1,[],X); Z<Y, append(L2,[],X)),!.

%Base de Conocimiento Ejercicio 3

lisclone(Lista):- append(X,X,Lista),!.