(ns zaeny.boot-atomizer
  {:boot/export-tasks true}
  (:require [clojure.java.io   :as io]
            [boot.core         :as boot :refer [deftask]]
            [boot.pod          :as pod]
            [boot.util         :as util]))

(deftask atomizer
  "Boot Task to parse atomizer css classNames"
  [o output-to PATH str "output css"
   r find-class PATH str "findClassName from source directory "
   p path-atomizer PATH str "atomizer exec location default global"]

  (let [output-path (or output-to "main.css")
        tmp-dir (boot/tmp-dir!)
        out (io/file tmp-dir output-path)
        find-source (or find-class "src")
        atomizer (or path-atomizer "atomizer")]
    ; call atomizer from shell
    (boot/with-pre-wrap fileset
        (let [out-file (doto (io/file tmp-dir "main.css") io/make-parents)]
          (util/info "Atomizer process %s" (.getName out-file))
          (apply util/dosh [atomizer "-o" (.getPath out-file) "-R" find-source]))
      ;commit to fileset
      (-> fileset
          (boot/add-resource tmp-dir)
          boot/commit!))))
