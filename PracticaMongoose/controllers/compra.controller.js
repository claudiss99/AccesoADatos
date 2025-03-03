const Compra = require('.../models/compra.model');
const { json } = require('express');

let CompraController = {};

//Consulta 9
CompraController.buyActivity = async (req, res) => {
    try{
        const {idActivity, idClient} = req.body;
        const cliente = await ClienteController.findById(idClient);
        const actividad = await Actividad.findById(idActivity);
        if(!cliente || !actividad){
            return res.status(404).json({message: 'Cliente o actividad no encontrada'});
        }

        if(actividad.plazas_disponibles > 0 && actividad.fecha > new Date()){
            const compra = new Compra({id_cliente: idClient, id_actividad:idActivity, fecha_compra: new Date()});
            await compra.save();

            actividad.plazas_disponibles -= 1;
            await actividad.save();
            res.status(201).json({message: 'Compra realizada con éxito'});
        }else{
            res.status(400).json({message: 'No hay plazas disponibles o la actividad ya ha pasado'});
        }
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

//Consulta 10
CompraController.cancelBuys = async (req, res) => {
    try{
        const {idActivity, idClient} = req.body;
        const compra = await Compra.findOne({id_cliente: idClient, id_actividad: idActivity});
        if(!compra){
            return res.status(404).json({message: 'Compra no encontrada'});
        }

        const actividad = await Actividad.findById(idActivity);
        if(!actividad || actividad.fecha <= new Date()){
            return res.status(400).json({message: 'No se puede cancelar, la actividad ya ha pasado'});
        }

        await Compra.findByIdAndDelete(compra._id);
        actividad.plazas_disponibles +=1;
        await actividad.save();

        res.json({message: 'Compra cancelada con éxito'});
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

module.exports = CompraController;