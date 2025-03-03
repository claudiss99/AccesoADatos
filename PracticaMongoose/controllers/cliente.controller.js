const Cliente = require('../models/cliente.model');
const Compra = require('../models/compra.model');

let ClienteController = {};

//Consulta 6
ClienteController.addCliente = async (req,res)  => {
    try{
        const cliente = new Cliente(req.body);
        await cliente.save();
        res.status(201).json({message: 'Cliente añadico con éxito', id: cliente._id});

    }catch(error){
        res.status(400).json({error : error.message});
    }
};

//Consulta 7
ClienteController.updateCliente = async (req,res) => {
    try{
        const {id} = req.params;
        const cliente = await Cliente.findByIdAndUpdate(id, req.body, {new:true});
        if(!cliente) return res.status(404).json({message: 'Cliente no encontrado'});
        res.json({message: 'Cliente actualizado', cliente});
    }catch(error){
        res.status(400).json({error: error.message});
    };
}

//Consulta 8
ClienteController.deleteCliente = async(req, res) => {
    try{ 
        const{id} =req.params;
        const compras = await Compra.find({id_cliente: id});
        const actividadesFuturas = [];

        for(const compra of compras){
            const actividad = await Actividad.findById(compra.id_actividad);
            if(actividad && actividad.fecha > new Date()){
                actividadesFuturas.push(actividad);
            }
        }

        if (actividadesFuturas.length >0){
            return res.status(400).json({message: 'No se puede eliminar, el cliente tiene actividades futuras'})
        }

        const resultado = await Cliente.findByIdAndDelete(id);
        if(!resultado) return res.status(404).json({message: 'Cliente no encontrado'});
        res.json({message: 'Cliente eliminado'});
    }catch(error){
        res.status(400).json({error: error.message})
    }
}

//Consulta 11
ClienteController.getAllClient =async (req, res) =>{
    try{
        const clientes = await Cliente.find();
        res.json(clientes);
    }catch(error){
        res.status(400).json({error: error.message});
    }
}

//Consulta 13
ClienteController.getDetailsClient =async (req, res) => {
    try{
        const {id} = req.params;
        const cliente = await Cliente.findById(id);
        if(!cliente){
            return res.status(404).json({message: 'Clientte no encontrado'});
        }
        const compras = await Compra.find({ id_cliente: id });
        res.json({cliente, compras});
    }catch(error){
        res.status(400).json({error: error.message});
    }
};

module.exports = ClienteController;