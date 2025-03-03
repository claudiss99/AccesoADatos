const express = require('express');
const CompraController = require('../controllers/compra.controller');

const router = express.Router();

router.post('/comprar', CompraController.buyActivity);
router.post('/cancelar', CompraController.cancelBuys);

module.exports = router;