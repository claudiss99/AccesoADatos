const Proveedor = require('.../models/proveedor.model');

let ProveedorController = {};

//Consulta 1
ProveedorController.addProveedor = async(req, res) => {
    try{
        const proveedor = new Proveedor(req.body);
        await proveedor.save();
        res.status(201).json({message: 'Proveedor añadido', id:proveedor._id});

    }catch(error){
        res.status(400).json({error: error.message});
    }
}

//Consulta 2
ProveedorController.updateProveedor = async (req, res) => {
    try{
        const {id} = req.params;
        const proveedor = await Proveedor.findByIdAndUpdate(id, req.body, {new:true});
        if(!proveedor) return res.status(404).json({message: 'Proveedor no encontrado'});
        res.json({message: 'Proveedor actualizado', proveedor});

    }catch(error){
        res.status(400).json({error: error.message});
    }
};

//Consulta 3
ProveedorController.deleteProveedor = async (req, res) => {
    try{
        const {id} = req.params;
        const actividades = await Actividad.find({id_proveedor: id, fecha: {$gt: new Date()}});
        if (actividades.length > 0){
            return res.status(400).json({message: 'No se puede eliminar, tiene actividades futuras'});

        }
        const resultado = await Proveedor.findByIdAndDelete(id);
        if(!resultado) return res.status(404).json({message: 'Proveedor no encontrado'});
        res.json({message: 'Proveedor eliminado'});

    }catch(error){
        res.status(400).json({error: error.message});
    }
};

//Consulta 14
ProveedorController.getDetailsProveedor() = async (req, res) => {
    try{
        const {id} = req.params;
        const proveedor = await Proveedor.findById(id);
        if(!proveedor){
            return res.status(404).json({message: 'Proveedor no encontrado'});
        }
        res.json(proveedor);
    }catch(error){
        res.status(400).json({error: error.message});
    }

};

module.exports = ProveedorController;