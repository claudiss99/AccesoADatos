const mongoose = require('mongoose');

const trabajadorSchema = new mongoose.Schema(
  {
    nombre: { type: String, required: true, trim: true },
    email: { type: String, required: true, trim: true, unique:true },
    telefono: { type: String, required: true, trim:true },
    
  },
);

module.exports = mongoose.model('Trabajador', trabajadorSchema, 'trabajadores');

