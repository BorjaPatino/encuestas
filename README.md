¿Qué sucede si intentas borrar una encuesta que no existe? ¿Cómo lo has controlado?
Si intentas borrar una encuesta que no existe, el sistema lanza una excepción controlada. Esto se maneja en el controlador para evitar que la aplicación falle y mostrar un mensaje de error adecuado.

Si introduces en un texto del tipo <style>body { background-color:red; }</style> en uno de los campos, ¿qué sucede al ver la encuesta? ¿El navegador ejecuta ese código o no? ¿Por qué? ¿Cómo podrías hacer que se ejecute ese código o que se deje de ejecutar?
El navegador no ejecuta ese código porque la entrada de datos está sanitizada (escapada) para evitar ataques XSS. Para que se ejecute, deberías desactivar la sanitización, pero esto es inseguro. Para evitar su ejecución, es necesario mantener el escape de las entradas.

¿Qué has tenido que modificar en el repositorio para filtrar por motivo de búsqueda? ¿Has tenido que escribir el código específico o cómo lo has realizado?
He creado un método personalizado en el repositorio que permite filtrar encuestas por motivo, y ajustado el controlador para recibir el parámetro de búsqueda.
