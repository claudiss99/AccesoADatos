const mongoose = require('mongoose');

const CompraSchema = new mongoose.Schema({
    id_cliente: {type: mongoose.Schema.Types.ObjectId, ref: 'Cliente'},
    id_actividad: {type: mongoose.Schema.Types.ObjectId, ref: 'Actividad'},
    fecha_compra: {type: Date, default: Date.now}
})


module.exports = mongoose.model('Compra', CompraSchema, 'compras')