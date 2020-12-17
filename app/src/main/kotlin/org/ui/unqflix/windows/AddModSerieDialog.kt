package org.ui.unqflix.windows

import domain.Category
import domain.Content
import org.ui.unqflix.appModel.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class AddModSerieDialog(owner: WindowOwner, model: SerieAppModel) : Dialog<SerieAppModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        Panel(mainPanel) with {
            asHorizontal()
            Panel(it) with {
                Label(it) with {
                    text = "Title:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("title")
                }
            }

            Panel(it) with {
                Label(it) with {
                    text = "Poster:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("poster")
                }
            }
        }
        Label(mainPanel) with {
            text = "  Description:"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()

            Panel(it) with {
                TextBox(it) with {
                    width = 200
                    height = 100
                    bindTo("description")
                }
            }

            Panel(it) with {
                asHorizontal()
                Label(it) withText "State:"
                CheckBox(it) with {
                    bindTo("stateBoolean")
                }
            }
        }
        Label(mainPanel) with {
            text = "  Categories:"
            alignLeft()
        }
        Panel(mainPanel) with {
            asHorizontal()
            Panel(it) with {
                Panel(it) with {
                    List<CategoryAppModel>(it) with {
                        width = 200
                        height = 150
                        bindItemsTo("selectedCategories").adaptWithProp<CategoryAppModel>("name")
                        bindSelectedTo("unSelectedCategory")
                    }
                }
            }

            botonesDePasaje(it)

            Panel(it) with {
                List<Category>(it) with {
                    width = 200
                    height = 150
                    bindItemsTo("categories").adaptWithProp<CategoryAppModel>("name")
                    bindSelectedTo("selectedCategory")
                }
            }
        }
        Label(mainPanel) with {
            text = "  Related Contents:"
            alignLeft()
        }

        Panel(mainPanel) with {
            asHorizontal()
            Panel(it) with {
                Panel(it) with {
                    List<Category>(it) with {
                        width = 200
                        height = 150
                        bindItemsTo("selectedRelatedContents").adaptWithProp<Content>("title")
                        bindSelectedTo("unSelectedContent")
                    }
                }
            }
 //           botonesDePasaje(mainPanel, f())
            Panel(it) with {
                Button(it) with {
                    caption = "<"
                    onClick { (modelObject as SerieAppModel).addContent()}
                }
                Button(it) with {
                    caption = ">"
                    onClick { (modelObject as SerieAppModel).removeContent() }
                }
            }
            Panel(it) with {
                List<Category>(it) with {
                    width = 200
                    height = 150
                    bindItemsTo("releatedContents").adaptWithProp<Content>("title")
                    bindSelectedTo("selectedContent")
                }
            }
        }
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick(Action { accept() })
            }
            Button(it) with {
                caption = "Cancel"
                onClick(Action { cancel() })
            }
        }
    }

    fun botonesDePasaje(panel: Panel) {
        Panel(panel) with
                {
                    Button(it) with {
                        caption = "<"
                        onClick { (modelObject as SerieAppModel).addCategory() }
                    }
                    Button(it) with {
                        caption = ">"
                        onClick { (modelObject as SerieAppModel).removeCategory() }
                    }
                }
    }
}