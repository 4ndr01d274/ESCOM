parent(pam,bob).
parent(tom,bob).
parent(tom,liz).
parent(bob,ann).
parent(bob,pat).
parent(pat,jim).
%Pondre mas personas para llenar mas el arbol.
parent(pam,liz).
parent(mar,ale).
parent(rob,ale).
parent(ale,ann).
parent(ale,pat).
parent(mar,juan).
parent(rob,juan).
parent(leo,jim).
parent(rub,ros).
parent(juan,ros).
parent(rub,leon).
parent(juan,leon).

female(mar).
female(ale).
female(pam).
female(liz).
female(pat).
female(ann).
female(rub).
female(ros).
male(jim).
male(tom).
male(bob).
male(rob).
male(juan).
male(leo).
male(leon).
mother(X,Y):-parent(X,Y),female(X).

father(X,Y):-parent(X,Y),male(X).

brother(X,Y):-parent(W,X),parent(W,Y),male(X),male(W),Y\==X.

sister(X,Y):-parent(W,X),parent(W,Y),female(X),female(W),Y\==X.

aunt(X,Y):-brother(X,W),parent(W,Y),male(X),W\==X.

uncle(X,Y):-sister(X,W),parent(W,Y),female(X),X\==W.

nephew(X,Y):-male(X),parent(W,X),parent(Z,Y),parent(Z,W),W\==Y.

niece(X,Y):-female(X),parent(W,X),parent(Z,Y),parent(Z,W),W\==Y.

couple(X,Y):-parent(X,W),male(X),parent(Y,W),female(Y).

cousin(X,Y):-parent(W,X),parent(Z,W),parent(Z,R),parent(R,Y),X\==Y,W\==R.

sisterinlow(X,Y):-couple(Y,Z),sister(X,Z).

brotherinlow(X,Y):-couple(Y,Z),brother(X,Z).

grandparent(X,Z):-parent(X,Y),
parent(Y,Z).

predecessor(X,Y):-parent(X,Y).%paso base
predecessor(X,Y):-parent(X,Z), predecessor(Z,Y).
% paso recursivo recursive case
%predecessor(X,Y):-parent(Z,Y), predecessor(X,Z).