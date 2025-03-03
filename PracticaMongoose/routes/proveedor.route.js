const express = require('express');
const ProveedorController = require('../controllers/proveedor.controller');

const router = express.Router();

router.post('/proveedores', ProveedorController.addProveedor);
router.put('/proveedores/:id', ProveedorController.updateProveedor);
router.delete('/proveedores/:id', ProveedorController.deleteProveedor);
router.get('/proveedores/:id', ProveedorController.getDetailsProveedor);

module.exports = router;