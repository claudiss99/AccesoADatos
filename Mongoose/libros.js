const express = require('express');
const app = express();

const {Libro} = require('./libreria.js');
const PORT = process.env.PORT||4000;

app.use(express.json());

app.listen(PORT, () => {
  console.log('Servidor corriendo en el puerto ${PORT}');
});

//Crear un libro
app.post('/libros', (req, res) => {
    const libro = req.body;
    const nuevoLibro = new Libro(libro);
    nuevoLibro.save();
    res.send('libro creado con exito');
  });


//Modificar un libro
app.put('/libros/:id', (req, res) => {
  Libro.findByIdAndUpdate(req.params.id, 
    req.body
  )
  .then((libro) => res.send('Libro actualizado correctamente'))
  .catch((err) => res.send('Error al actualizar el libro '+err))
});

//Eliminar un libro
app.delete('/libros/:id', (req, res) => {
  Libro.findByIdAndDelete(req.params.id)
  .then((libro) => res.send(libro ? 'Libro borrado correctamente': 'Libro no encontrado'))
  .catch((err) => res.send('Error al actualizar el libro '+err))

});

//Consultar todos los libros
app.get('/libros', (req, res) => {
  Libro.find()
  .then((libros) => res.send(libros))
  .catch((err) => res.send('Error al consultar: ', err))
});

//Consultar libro por id
app.get('/libros/:id', (req, res) => {
  Libro.findById(req.params.id)
  .then((libros) => res.send(libros))
  .catch((err) => res.send('Error al consultar por id: ', err))
});

//Consultar libro por fragmento
app.get('/libros/:fragmento', (req, res) => {
  const fragmento = req.query.fragmento;
  const regex = new RegExp(fragmento, 'i');
  Libro.find({titulo: {$regex:regex}})
  .then((libros) => res.send(libros))
  .catch((err) => res.send('Error consultar por fragmento: ',err))
})


