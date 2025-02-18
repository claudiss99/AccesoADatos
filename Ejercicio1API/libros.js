const express = require('express');
const app = express();

//Crear un libro
app.post('/libros/', (req, res) => {
    res.send('libro creado con exito');
  });

//Modificar libro
app.post('/libros/:id', (req, res) => {
    const libroId = req.params.id;
    res.send(`Modificación del libro con ID: ${libroId}`);
  });


//Eliminar un libro
app.delete('/libros/:id', (req, res) => {
    res.send('Libro borrado con éxito');
});

//Consultar todos los libros
app.get('/libros/', (req, res) => {
  res.send(`Consultando todos los libros`);
});

//Consultar libro por id
app.get('/libros/:id', (req, res) => {
  // Acceder a la variable de la ruta
  const libroId = req.params.id;
  res.send(`Información del libro con ID: ${libroId}`);
});

//Buscar libros por titulo
app.get('/libros/:titulo', (req, res) => {
    // Acceder a la variable de la ruta
    const libroTitulo = req.params.id;
    res.send(`Información del libro con titulo: ${libroTitulo}`);
  });

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en el puerto ${PORT}`);
});