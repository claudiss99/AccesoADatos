const express = require('express');
const ProveedorController = require('../controllers/proveedor.controller');

const router = express.Router();

router.post('/', ProveedorController.addProveedor);
router.put('/:id', ProveedorController.updateProveedor);
router.delete('/:id', ProveedorController.deleteProveedor);
router.get('/:id', ProveedorController.getDetailsProveedor);

module.exports = router;