PGDMP                         y            Mixup    13.4    13.4 #    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24867    Mixup    DATABASE     d   CREATE DATABASE "Mixup" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Mexico.1252';
    DROP DATABASE "Mixup";
                postgres    false            �            1259    24933    camaras    TABLE     �   CREATE TABLE public.camaras (
    numerocam integer NOT NULL,
    turno character(15) NOT NULL,
    id_tiendacam integer NOT NULL
);
    DROP TABLE public.camaras;
       public         heap    postgres    false            �            1259    24873 	   comprador    TABLE     f   CREATE TABLE public.comprador (
    curpcom character(18) NOT NULL,
    numerocom integer NOT NULL
);
    DROP TABLE public.comprador;
       public         heap    postgres    false            �            1259    24883    cuenta    TABLE     �   CREATE TABLE public.cuenta (
    numero integer NOT NULL,
    forma_de_pago character(15) NOT NULL,
    precio numeric(8,2) NOT NULL,
    fecha date,
    compra character(20) NOT NULL,
    curpcue character(18) NOT NULL
);
    DROP TABLE public.cuenta;
       public         heap    postgres    false            �            1259    24893    empleado    TABLE     �   CREATE TABLE public.empleado (
    curpemp character(18) NOT NULL,
    no_empleado integer NOT NULL,
    salario numeric(8,2) NOT NULL
);
    DROP TABLE public.empleado;
       public         heap    postgres    false            �            1259    24868    persona    TABLE     �   CREATE TABLE public.persona (
    curp character(18) NOT NULL,
    fecha_nacimiento date,
    nombre character(15) NOT NULL,
    appat character(15) NOT NULL,
    apmat character(20),
    sexo character(1),
    direccion character(30) NOT NULL
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    24918 	   productos    TABLE       CREATE TABLE public.productos (
    codigo_barras integer NOT NULL,
    unidades integer NOT NULL,
    nombrepro character(20) NOT NULL,
    marca character(15),
    precio numeric(12,2) NOT NULL,
    anio integer,
    curppro character(18) NOT NULL,
    numeropro integer NOT NULL
);
    DROP TABLE public.productos;
       public         heap    postgres    false            �            1259    24908 	   secciones    TABLE     �   CREATE TABLE public.secciones (
    numerosec integer NOT NULL,
    lugar character(30),
    nombresec character(15) NOT NULL,
    no_trabajadores integer,
    id_tiendasec integer NOT NULL
);
    DROP TABLE public.secciones;
       public         heap    postgres    false            �            1259    24903    tienda    TABLE     �   CREATE TABLE public.tienda (
    direcciontie character(30) NOT NULL,
    nombretie character(20),
    id_tienda integer NOT NULL
);
    DROP TABLE public.tienda;
       public         heap    postgres    false            �          0    24933    camaras 
   TABLE DATA           A   COPY public.camaras (numerocam, turno, id_tiendacam) FROM stdin;
    public          postgres    false    207   U(       �          0    24873 	   comprador 
   TABLE DATA           7   COPY public.comprador (curpcom, numerocom) FROM stdin;
    public          postgres    false    201   r(       �          0    24883    cuenta 
   TABLE DATA           W   COPY public.cuenta (numero, forma_de_pago, precio, fecha, compra, curpcue) FROM stdin;
    public          postgres    false    202   �(       �          0    24893    empleado 
   TABLE DATA           A   COPY public.empleado (curpemp, no_empleado, salario) FROM stdin;
    public          postgres    false    203   �(       �          0    24868    persona 
   TABLE DATA           `   COPY public.persona (curp, fecha_nacimiento, nombre, appat, apmat, sexo, direccion) FROM stdin;
    public          postgres    false    200   �(       �          0    24918 	   productos 
   TABLE DATA           p   COPY public.productos (codigo_barras, unidades, nombrepro, marca, precio, anio, curppro, numeropro) FROM stdin;
    public          postgres    false    206   �(       �          0    24908 	   secciones 
   TABLE DATA           _   COPY public.secciones (numerosec, lugar, nombresec, no_trabajadores, id_tiendasec) FROM stdin;
    public          postgres    false    205   )       �          0    24903    tienda 
   TABLE DATA           D   COPY public.tienda (direcciontie, nombretie, id_tienda) FROM stdin;
    public          postgres    false    204    )       K           2606    24937    camaras cvecam 
   CONSTRAINT     S   ALTER TABLE ONLY public.camaras
    ADD CONSTRAINT cvecam PRIMARY KEY (numerocam);
 8   ALTER TABLE ONLY public.camaras DROP CONSTRAINT cvecam;
       public            postgres    false    207            ?           2606    24877    comprador cvecom 
   CONSTRAINT     S   ALTER TABLE ONLY public.comprador
    ADD CONSTRAINT cvecom PRIMARY KEY (curpcom);
 :   ALTER TABLE ONLY public.comprador DROP CONSTRAINT cvecom;
       public            postgres    false    201            A           2606    24887    cuenta cvecue 
   CONSTRAINT     O   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cvecue PRIMARY KEY (numero);
 7   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cvecue;
       public            postgres    false    202            C           2606    24897    empleado cveemp 
   CONSTRAINT     R   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT cveemp PRIMARY KEY (curpemp);
 9   ALTER TABLE ONLY public.empleado DROP CONSTRAINT cveemp;
       public            postgres    false    203            =           2606    24872    persona cveper 
   CONSTRAINT     N   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT cveper PRIMARY KEY (curp);
 8   ALTER TABLE ONLY public.persona DROP CONSTRAINT cveper;
       public            postgres    false    200            I           2606    24922    productos cvepro 
   CONSTRAINT     Y   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT cvepro PRIMARY KEY (codigo_barras);
 :   ALTER TABLE ONLY public.productos DROP CONSTRAINT cvepro;
       public            postgres    false    206            G           2606    24912    secciones cvesec 
   CONSTRAINT     U   ALTER TABLE ONLY public.secciones
    ADD CONSTRAINT cvesec PRIMARY KEY (numerosec);
 :   ALTER TABLE ONLY public.secciones DROP CONSTRAINT cvesec;
       public            postgres    false    205            E           2606    24907    tienda cvetie 
   CONSTRAINT     R   ALTER TABLE ONLY public.tienda
    ADD CONSTRAINT cvetie PRIMARY KEY (id_tienda);
 7   ALTER TABLE ONLY public.tienda DROP CONSTRAINT cvetie;
       public            postgres    false    204            R           2606    24938    camaras cvecamtie    FK CONSTRAINT     �   ALTER TABLE ONLY public.camaras
    ADD CONSTRAINT cvecamtie FOREIGN KEY (id_tiendacam) REFERENCES public.tienda(id_tienda) ON UPDATE CASCADE ON DELETE SET NULL;
 ;   ALTER TABLE ONLY public.camaras DROP CONSTRAINT cvecamtie;
       public          postgres    false    2885    204    207            P           2606    24923    productos cvecur    FK CONSTRAINT     �   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT cvecur FOREIGN KEY (curppro) REFERENCES public.empleado(curpemp) ON UPDATE CASCADE ON DELETE SET NULL;
 :   ALTER TABLE ONLY public.productos DROP CONSTRAINT cvecur;
       public          postgres    false    206    203    2883            N           2606    24898    empleado cvecure    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT cvecure FOREIGN KEY (curpemp) REFERENCES public.persona(curp) ON UPDATE CASCADE ON DELETE SET NULL;
 :   ALTER TABLE ONLY public.empleado DROP CONSTRAINT cvecure;
       public          postgres    false    203    2877    200            M           2606    24888    cuenta cvecurp    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cvecurp FOREIGN KEY (curpcue) REFERENCES public.comprador(curpcom) ON UPDATE CASCADE ON DELETE SET NULL;
 8   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cvecurp;
       public          postgres    false    2879    202    201            L           2606    24878    comprador cvecurpcom    FK CONSTRAINT     �   ALTER TABLE ONLY public.comprador
    ADD CONSTRAINT cvecurpcom FOREIGN KEY (curpcom) REFERENCES public.persona(curp) ON UPDATE CASCADE ON DELETE SET NULL;
 >   ALTER TABLE ONLY public.comprador DROP CONSTRAINT cvecurpcom;
       public          postgres    false    201    200    2877            O           2606    24913    secciones cveid    FK CONSTRAINT     �   ALTER TABLE ONLY public.secciones
    ADD CONSTRAINT cveid FOREIGN KEY (id_tiendasec) REFERENCES public.tienda(id_tienda) ON UPDATE CASCADE ON DELETE SET NULL;
 9   ALTER TABLE ONLY public.secciones DROP CONSTRAINT cveid;
       public          postgres    false    204    2885    205            Q           2606    24928    productos cvenum    FK CONSTRAINT     �   ALTER TABLE ONLY public.productos
    ADD CONSTRAINT cvenum FOREIGN KEY (numeropro) REFERENCES public.secciones(numerosec) ON UPDATE CASCADE ON DELETE SET NULL;
 :   ALTER TABLE ONLY public.productos DROP CONSTRAINT cvenum;
       public          postgres    false    205    2887    206            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     