const Tarea = require('../models/tarea.model');

let TareaController = {};

TareaController.creaTarea= async (req, res) => {
    try {
        const nuevaTarea = new Tarea(req.body);
        const tareaGuardada = await nuevaTarea.save();
        res.json(tareaGuardada);
    } catch (error) {
        res.status(500).json({error: 'Error al crear la tarea'});
    }
}

TareaController.modificarTarea = async (req, res) => {
    try {
        const tareaActualizada = await Tarea.findByIdAndUpdate(req.params.id, req.body, {new : true});
        if (!tareaActualizada) {
            return res.status(404).json({error: 'Tarea no encontrado'});
        }
        res.json(tareaActualizada);
    } catch (error) {
        res.status(500).json({error: 'Error al actualizar una tarea'});
    }
}

TareaController.obtenerTareasPorTrabajador = async (req, res) => {
    try{
        const tareas = await Tarea.find({trabajador: req.params.trabajadorId});
        res.json(tareas);
    }catch(error){
        res.status(500).json({error: 'Error al obtener tareas del trabajador'});
    }
}

Trabajador.obtenerTareasCompletadasOPendientesPorTrabajador = async (req, res) => {
   try{
        const tareas = await Tarea.find({trabajador: req.params.trabajadorId, estado: req.query.estado});
        res.json(tareas)
   }catch(error){
        res.status(500).json({error: 'Error al obtener las tareas segun estado'});
   }
}

Trabajador.obtenerTareaPorId = async (req, res) => {
    try {
        const tarea = await Tarea.findById(req.params.id);
        if (tarea) {
            res.json(tarea);
        } else {
            res.status(404).json({error: 'No se ha encontrado ningún tarea con el ID ' + req.params.id});
        }
    } catch (error) {
        res.status(500).json({error: 'Error al obtener la tarea con ID: ' + req.params.id});
    }
}

Trabajador.marcarTareaCompletada = async (req, res) => {
    try {
        const tareaActualizada = await Tarea.findByIdAndUpdate(req.params.id, {estado: 'Completada'}, {new : true});
        if (!tareaActualizada) {
            return res.status(404).json({error: 'Tarea no encontrado'});
        }
        res.json('Tarea marcada como completada', tareaActualizada);
    } catch (error) {
        res.status(500).json({error: 'Error al actualizar una tarea'});
    }
}

module.exports = TareaController;