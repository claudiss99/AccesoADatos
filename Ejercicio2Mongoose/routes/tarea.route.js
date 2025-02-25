const express = require('express');
const TareaController = require('../controllers/tarea.controller');

const router = express.Router();

router.post('/', TareaController.crearTarea);
router.put('/:id', TareaController.modificarTarea);
router.get('/trabajador/:trabajadorId', TareaController.obtenerTareasPorTrabajador);
router.get('/trabajador/:trabajadorId/estado', TareaController.obtenerTareasCompletadasOPendientesPorTrabajador);
router.get('/:id', TareaController,obtenerTareaPorId);
router.put('/:id/completada', TareaController.marcarTareaCompletada);

module.exports = router;