# Boot-Atomizer
[![Clojars Project](http://clojars.org/zaeny/boot-atomizer/latest-version.svg)](http://clojars.org/zaeny/boot-atomizer)

[Boot](http://boot-clj.com) Task for [Atomizer](http://acss.io) to compile atomic css

## Installation
`npm install -g atomizer`
or install modules local and add to `path-atomizer`

## Usage
Require dependencies :
```clojure
  (set-env! :dependencies '[[zaeny/boot-atomizer "0.1.0"]])
  ;and task
  (require '[zaeny/boot-atomizer :refer [atomizer])
```

Set task before cljs :
```clojure
(deftask dev []
  (comp
    ; (serve)
    (watch)
    (atomizer :find-class "src"
              :output-to "main.css"
              :path-atomizer "node_modules/.bin/atomizer")
    ; (reload :on-jsload 'app.core/on-jsload
    ;         :cljs-asset-path ".")
    ; (cljs-repl)
    (cljs :source-map true)))
```

## Options for cli
```
  [o output-to PATH str "output css"
   r find-class PATH str "findClassName from source directory "
   p path-atomizer PATH str "atomizer exec location default global"]
```

** Example usage from terminal: **
`boot atomizer -o main.css -r src/ `
Atomizer will try to parse from src directory
