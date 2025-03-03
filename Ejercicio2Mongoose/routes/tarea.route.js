const express = require('express');
const TareaController = require('../controllers/tarea.controller');

const router = express.Router();

router.post('/:trabajadorId/tareas', TareaController.crearTarea);
router.put('/:trabajadorId/tareas/:tareaId', TareaController.modificarTarea);
router.get('/trabajador/:trabajadorId', TareaController.obtenerTareasPorTrabajador);
router.get('/trabajador/:trabajadorId/tarea/estado', TareaController.obtenerTareasCompletadasOPendientesPorTrabajador);
router.get('/:id', TareaController,obtenerTareaPorId);
router.put('/:id/completada', TareaController.marcarTareaCompletada);

module.exports = router;