(set-env!
  :resource-paths #{"src"}

  :repositories [["clojars" {:url "https://clojars.org/repo/"
                             :username (System/getenv "CLOJARS_USER")
                             :password (System/getenv "CLOJARS_PASS")}]])

(def +version+ "0.1.1")

(task-options!
  push {:repo "clojars"}
  pom {:project 'zaeny/boot-atomizer
       :version +version+
       :description "Boot task to compile css classes using Atomizer "
       :url "https://github.com/azizzaeny/boot-atomizer"
       :scm {:url "https://github.com/azizzaeny/boot-atomizer"}
       :license {"MIT License" "https://opensource.org/licenses/MIT"}})

(deftask build []
  (comp
   (pom)
   (jar)
   (install)))

(deftask push-release []
 (comp
   (build)
   (push :repo "clojars")))
