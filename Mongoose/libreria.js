const express = require('express');
const app = express();
const mongoose = require('mongoose');

// Conectar a MongoDB
mongoose.connect('mongodb+srv://claudiss99:claudiss648912204@insti.aldna.mongodb.net/Libreria?retryWrites=true&w=majority&appName=Insti')
  .then(() => console.log('Conectado a MongoDB'))
  .catch((err) => console.log('Error de conexión:', err));


//Modificar un libro
app.post('/libros/', (req, res) => {
    const titulo = req.body.titulo;
    const autor = req.body.Autor;
    const year = req.body.year;
    const editorial =req.body.editorial;
    res.send("Modificado libro con éxito")
  });

// Definir un esquema
const productoSchema = new mongoose.Schema({
  titulo: { type: String, required: true },
  Autor: { type: String, required: true },
  year: { type: int, default: 0 },
  editorial: {type:String, required:true}
});

// Crear un modelo a partir del esquema
const Producto = mongoose.model('Libreria', productoSchema);

// Crear un nuevo producto
const nuevoProducto = new Producto({
  nombre: 'Camiseta',
  precio: 20,
  stock: 50
});

// Guardar el producto en la base de datos
nuevoProducto.save()
  .then(() => console.log('Producto guardado en la base de datos'))
  .catch((err) => console.log('Error al guardar el producto:', err));

// Consultar todos los productos
Producto.find()
  .then((productos) => console.log(productos))
  .catch((err) => console.log('Error al consultar productos:', err));