const express = require('express');
const ActividadController = require('../controllers/actividad.controller');

const router = express.Router();

router.post('/actividades', ActividadController.addActividad);
router.delete('/actividades/:id', ActividadController.deleteActivity);
router.get('/actividades/futuras', ActividadController.getFutureActivities);
router.get('/actividades/:id', ActividadController.getDetailsActivity);

module.exports = router;