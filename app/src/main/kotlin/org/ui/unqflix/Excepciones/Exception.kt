package org.ui.unqflix.Excepciones

import org.uqbar.commons.model.exceptions.UserException

class Exception {
    companion object {
        fun checkTitle(title: String,s :String) {
            if (title.isBlank()) throw UserException("Una " + s + " no debe tener título en blanco")
        }
        fun checkSelected(any: Any?,s:String){
            if (any === null) {
                throw UserException("No ha seleccionado una  "+ s + " . Por favor, cierre esta ventana y seleccione una.")
            }
        }
    }

}