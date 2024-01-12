%Funciones Recursivas con Listas
%Funciones que calculan el tamaño (length) de una lista
%Caso Base
longitud([],0). %Caso Base Clausula (Hecho o Fact)
longitud([_],1).  %Caso Base
longitud([_,_],2).
longitud([_|R],Longitud):-longitud(R,LongitudResto),
         Longitud is LongitudResto+1.

sumatoria([],0). %Caso Base
sumatoria([Unico],Unico). %Caso Base
sumatoria([S|R],Sumatoria):-sumatoria(R,SumatoriaResto),
    Sumatoria is SumatoriaResto+S.

producto([],0). %Caso Base
producto([Unico],Unico).
producto([P|R],Producto):-producto(R,ProductoResto),
    Producto is ProductoResto*P.









