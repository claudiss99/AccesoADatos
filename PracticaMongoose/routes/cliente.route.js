const express = require('express');
const ClienteController = require('../controllers/cliente.controller');

const router = express.Router();

router.post('/', ClienteController.addCliente);
router.put('/:id', ClienteController.updateCliente);
router.delete('/:id', ClienteController.deleteCliente);
router.get('/', ClienteController.getAllClient);
router.get('/:id', ClienteController.getDetailsClient);

module.exports = router;