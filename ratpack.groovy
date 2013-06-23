@GrabResolver("https://oss.jfrog.org/artifactory/repo")
@Grab(value = "org.ratpack-framework:ratpack-groovy:0.9.0-SNAPSHOT")

import org.ratpackframework.groovy.templating.TemplateRenderer
import org.ratpackframework.groovy.templating.TemplatingModule
import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
    modules {
        get(TemplatingModule).setCacheSize(0)
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
          get(TemplateRenderer).render "edit.html", id: "${pathTokens.id}"
        }		
		
		// http://localhost:5050/edit/2
        get("delete/:id"){
          get(TemplateRenderer).render "delete.html", id: "${pathTokens.id}"
        }				

        assets "public"
    }
}
