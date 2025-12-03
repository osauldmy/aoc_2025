My solutions this year in Clojure.

There are no input files in the repo, since aoc [about](https://adventofcode.com/2025/about) section asks not to include puzzle texts.

There are no tests, CI, etc as well, as it's not production code, I'm just having fun learning new language.

Developed and mostly evaluated in REPL, sometimes I uncomment "main" section and run as

`clj -M aoc/day01.clj`

REPL setup:

```clojure
;; ~/.config/clojure/deps.edn
{:aliases
  {:nrepl {:extra-deps {nrepl/nrepl {:mvn/version "1.5.1"}
                        cider/cider-nrepl {:mvn/version "0.58.0"}}
           :main-opts
           ["-m" "nrepl.cmdline"
            "--middleware" "[cider.nrepl/cider-middleware]"
            "--interactive"]}}}
```

Run REPL in separate terminal/tmux pane `clj -M:nrepl`.

Then connect to running nREPL from your editor of choice,
I used neovim with [conjure](https://github.com/Olical/conjure)
