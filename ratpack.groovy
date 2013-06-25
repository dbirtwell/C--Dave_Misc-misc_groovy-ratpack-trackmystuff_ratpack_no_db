@GrabResolver("https://oss.jfrog.org/artifactory/repo")
@Grab(value = "org.ratpack-framework:ratpack-groovy:0.9.0-SNAPSHOT")

import org.ratpackframework.groovy.templating.TemplateRenderer
import org.ratpackframework.groovy.templating.TemplatingModule
import static org.ratpackframework.groovy.RatpackScript.ratpack

import java.util.logging.Logger

ratpack {

    def logger = Logger.getLogger("")
	
    modules {
        get(TemplatingModule).setCacheSize(0)
		
		// TODO:
		// Enable non persistent map based session storage
        // register(new MapSessionsModule(10, 5))
    }
    
    handlers {

        // default route
        get {
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"
        }
		
		get ("new") {
            get(TemplateRenderer).render "new.html", title: "Groovy Track My Stuff"
		}

		// http://localhost:5050/edit/23
        get("edit/:id"){
		  // TODO: retrieve the item and passs along the info to populate the form
          get(TemplateRenderer).render "edit.html", id: "${pathTokens.id}"
        }		
		
		// http://localhost:5050/edit/2
        get("delete/:id"){
		  // TODO: retrieve the item and get the name and any other info
          // get(TemplateRenderer).render "delete.html", id: "${pathTokens.id}", name: ${pathTokens.name}
          get(TemplateRenderer).render "delete.html", id: "${pathTokens.id}"
        }
		
		// Data posted from a form
		post ("submit") {
            // get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"
			def form = request.form
            response.send "Now saving: " + form.name + " of type: " + form.item_type
        }								

		// --------------------------------------------------------------------
		// POSTs
		// --------------------------------------------------------------------
		
        //// ??? Is this correct?		
		//post ("delete/delete/:id") {
        //    response.send "Now deleting ID: ${pathTokens.id}"
        //}							
		
        // ??? Is this correct?
		// http://localhost:5050/delete/item/1
		post ("delete/item/:id") {
            response.send "Now deleting item with ID: ${pathTokens.id}"
        }									

        assets "public"
    }
}
