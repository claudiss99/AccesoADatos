const mongoose = require('mongoose');

const ActividadSchema = new mongoose.Schema({
    nombre: {type: String, required:true},
    fecha: {type:Date, required:true},
    ubicacion: {type:String, required:true},
    plazas_disponibles: { type: Number, required:true},
    id_proveedor: {type: mongoose.Schema.Types.ObjectId, ref: 'Actividad'}
})

module.exports = mongoose.model('Actividad', ActividadSchema, 'actividades')