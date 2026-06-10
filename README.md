Informe del feature "Computadoras"

Empezando por el codigo, lo primero que se creo fue "Computadora.java"
este archivo se encarga de sentar las bases de que es una computadora para el sistema.
Cuenta con sus variables basicas:
"int id_computadora" en representacion al numero ID de
caracter unico para cada computadora, asignado de manera automatica desde la base de
datos, este es un ID de uso unicamente en conjunto con la base de datos con la intencion
de ser un metodo de seguridad para el listado de computadoras en este cybercafe. El 
usuario no tiene acceso a este numero.

"int numero_pc" es el ID que se asigna desde la propia app, un numero con el mismo
caracter identificatorio pero visible en la app, que si bien es generado de manera
incrementoria como "id_computadora", este el usuario tiene la opcion de editarlo si
asi lo desea. Esta edicion cuenta con una verificacion de seguridad para que ninguna
computadora tenga el mismo ID y este compuesto unicamente con caracteres numericos.

"String estado" es la variable encargada de mostrar el estado de uso de cada computadora,
"Libre" si esta esta fuera de uso, "Ocupada" cuando hay un cliente haciendo uso de la 
misma, y "Mantenimiento" si la computadora esta actualmente fuera de servicio.

Luego le siguen sus respectivos Geeters y Setters.

-----------------------------------------------------------------------------------------
