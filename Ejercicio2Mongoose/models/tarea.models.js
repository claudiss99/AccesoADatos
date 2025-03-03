
const mongoose = require('mongoose');

const tareaSchema = new mongoose.Schema(
  {
    titulo: { type: String, required: true},
    descripcion: { type: String, required: true},
    estado: { type: String, required: true, default:"pendiente", trim: true },
    trabajador: { type: mongoose.Schema.Types.ObjectId, ref: 'Trabajador', required: true },
  },
);

module.exports = mongoose.model('Tarea', tareaSchema, 'tareas');

