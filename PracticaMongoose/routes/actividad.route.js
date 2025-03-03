const express = require('express');
const ActividadController = require('../controllers/actividad.controller');

const router = express.Router();

router.post('/', ActividadController.addActividad);
router.delete('/:id', ActividadController.deleteActivity);
router.get('/futuras', ActividadController.getFutureActivities);
router.get('/:id', ActividadController.getDetailsActivity);

module.exports = router;