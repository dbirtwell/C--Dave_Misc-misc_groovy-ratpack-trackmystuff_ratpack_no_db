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
        // Only initialize this once
        // def util = new TarotDeckUtil()

        // default route
        get {
            get(TemplateRenderer).render "index.html", title: "Groovy Track My Stuff"
        }
		
		get ("new") {
            get(TemplateRenderer).render "new.html", title: "Groovy Track My Stuff"
		}

        //// Actual dynamic part
        //get ("tarot") {
        //    def card = util.randomCard()
        //    get(TemplateRenderer).render "tarot.html", name: card.name, meaning: card.meaning, image: "images\\$card.image"
        //}

        get ("about") {
            get(TemplateRenderer).render "about.html", title: "Groovy Track My Stuff"
        }

        assets "public"
    }
}
