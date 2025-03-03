const express = require('express');
const ClienteController = require('../controllers/cliente.controller');

const router = express.Router();

router.post('/clientes', ClienteController.addCliente);
router.put('/clientes/:id', ClienteController.updateCliente);
router.delete('/clientes/:id', ClienteController.deleteCliente);
router.get('/clientes', ClienteController.getAllClient);
router.get('/clientes/:id', ClienteController.getDetailsClient);

module.exports = router;