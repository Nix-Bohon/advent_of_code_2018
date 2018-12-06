# advent_of_code_2018
Advent of Code 2018 Solutions (https://adventofcode.com/2018)

## Day One
Part one was pretty easy and natural to code, but part two took a bit more work, and I'm not totally
happy with how i ended up doing it. I want to refactor to use a `loop` instead of a `while`.

[Clojure Solution](day_one/solution.clj)

## Day Two
Part two presented a good opportunity to use the `for` function.

[Clojure Solution](day_two/solution.clj)

## Day Three
This one ended up giving me a lot more trouble (and time) than I initially anticipated, but I ended up finding a pretty reasonable way to approach it.

[Clojure Solution](day_three/solution.clj)

## Day Four
I'm starting to feel more comfortable in Clojure. I feel like I am struggling against the basic syntax less, but I think I still prefer F#.

[Clojure Solution](day_four/solution.clj)

## Day Five
Initially, I solved this one with a method that used `loop` and performed all of the reactions in a single pass. It worked and was neat, but it was pretty slow. The solution I ended up checking in uses regex instead and is significantly faster which was great for the second part.

[Clojure Solution](day_five/solution.clj)