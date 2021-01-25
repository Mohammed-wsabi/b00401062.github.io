# Boswell's The Art of Readable Code

- **Publisher**: O’Reilly
- **Author**: Dustin Boswell
- **Presenter**: Wen-Bin Luo
- **Link**: https://www.amazon.com/Art-Readable-Code-Practical-Techniques/dp/0596802293

## Contents

- [Surface-Level Improvements](#surface-level-improvements)
- [Simplifying Loops and Logic](#simplifying-loops-and-logic)

## Surface-Level Improvements

- [Packing Information into Names](#packing-information-into-names)
- [Names That Can’t Be Misconstrued](#names-that-cant-be-misconstrued)
- [Aesthetics](#aesthetics)
- [Knowing What to Comment](#knowing-what-to-comment)
- [Making Comments Precise and Compact](#making-comments-precise-and-compact)

### Packing Information into Names

- Pack information into your names.
- The name retval doesn’t pack much information. Instead, use a name that describes the variable’s value.
- The name tmp should be used only in cases when being short-lived and temporary is the most important fact about that variable.
- If you’re going to use a generic name like tmp, it, or retval, have a good reason for doing so.
- Having different formats for different entities is like a form of syntax highlighting.

### Names That Can’t Be Misconstrued

- The best names are ones that can’t be misconstrued.
- The clearest way to name a limit is to put max_ or min_ in front of the thing being limited.
- When naming a boolean, use words like is/has/can/use to make it clear that it’s a boolean.

### Aesthetics

- If multiple blocks of code are doing similar things, try to give them the same silhouette.
- Aligning parts of the code into “columns” can make code easy to skim through.
- Pick a meaningful order and stick with it.
- Use empty lines to break apart large blocks into logical “paragraphs.”
- Consistent style is more important than the “right” style.

### Knowing What to Comment

- The purpose of commenting is to help the reader know as much as the writer did.
- Don’t comment on facts that can be derived quickly from the code itself.
- Rule: good code > bad code + good comments.

### Making Comments Precise and Compact

- Avoid pronouns like “it” and “this” when they can refer to multiple things.
- Describe a function’s behavior with as much precision as is practical.
- Illustrate your comments with carefully chosen input/output examples.
- State the high-level intent of your code, rather than the obvious details.
- Use inline comments to explain mysterious function arguments.
- Keep your comments brief by using words that pack a lot of meaning.
- Comments should have a high information-to-space ratio.

## Simplifying Loops and Logic

- [Making Control Flow Easy to Read](#making-control-flow-easy-to-Read)

### Making Control Flow Easy to Read

- Make conditionals, loops, and other changes to control flow as "natural" as possible.
- A guideline that can be useful:
    - Left-hand side: The expression "being interrogated," whose value is more in flux.
    - Right-hand side: The expression being compared against, whose value is more constant.
- Instead of minimizing the number of lines, a better metric is to minimize the time needed for someone to understand it.
- By default, use an if/else. The ternary ?: should be used only for the simplest cases.
- Look at your code from a fresh perspective when you’re making changes. Step back and look at it as a whole.
