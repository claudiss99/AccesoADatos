
const mongoose = require('mongoose');

// Conectar a MongoDB
mongoose.connect('mongodb+srv://claudiss99:claudiss648912204@insti.aldna.mongodb.net/Libreria?retryWrites=true&w=majority&appName=Insti')
  .then(() => console.log('Conectado a MongoDB'))
  .catch((err) => console.log('Error de conexión:', err));


// Definir un esquema
const productoSchema = new mongoose.Schema({
  titulo: { type: String, required: true },
  autor: { type: String, required: true },
  year: { type: Number, default: 0 },
  editorial: {type:String, required:true}
});

// Crear un modelo a partir del esquema
const Libro = mongoose.model('Libro', productoSchema);

module.exports = {Libro};