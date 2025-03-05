const Actividad = require('../models/actividad.model');
const Proveedor = require('../models/proveedor.model');
const Cliente = require('../models/cliente.model')

let ActividadController = {};

//Consulta 4
ActividadController.addActividad = async(req, res) => {
    try{
        const{cif} = req.body;
        const proveedor = await Proveedor.findOne({cif});
        if(!proveedor){
            return res.status(404).json({message: 'Proveedor no encontrado'});
        }

        const actividad = new Actividad({...req.body, id_proveedor: proveedor._id});
        await actividad.save();
        res.status(201).json({message: 'Actividad añadida', id: actividad._id});
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

//Consulta 5
ActividadController.deleteActivity = async (req, res) => {
    try{
        const {id} = req.params;
        const actividad = await Actividad.findByIdAndDelete(id);
        if(!actividad){
            return res.status(404).json({message: 'Actividad no encontrada'});
        }
        res.json({message: 'Actividad eliminada'});
    }catch(error){
        res.status(400).json({error: error.message});
    }
}

//Consulta 12
ActividadController.getFutureActivities = async (req, res) => {
    try{
        const actividades = await Actividad.find({fecha: {$gt: new Date()}}).populate("id_proveedor");
        res.json(actividades);
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

//Consulta 15
ActividadController.getDetailsActivity = async (req, res) => {
    try{
        const {id} = req.params;
        const actividad = await Actividad.findById(id);
        if(!actividad){
            return res.status(404).json({message: 'Actividad no encontrada'});
        }

        const clientes = await Cliente.find({id_actividad: id})

        res.json({actividad, clientes});
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

module.exports = ActividadController;