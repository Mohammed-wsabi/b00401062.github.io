# Boswell's The Art of Readable Code

- **Publisher**: O’Reilly
- **Author**: Dustin Boswell
- **Presenter**: Wen-Bin Luo
- **Link**: https://www.amazon.com/Art-Readable-Code-Practical-Techniques/dp/0596802293

## Contents

- [Surface-Level Improvements](#surface-level-improvements)

## Surface-Level Improvements

- [Packing Information into Names](#packing-information-into-names)
- [Names That Can’t Be Misconstrued](#names-that-cant-be-misconstrued)

### Packing Information into Names

- Pack information into your names.
- The name retval doesn’t pack much information. Instead, use a name that describes the variable’s value.
- The name tmp should be used only in cases when being short-lived and temporary is the most important fact about that variable.
- If you’re going to use a generic name like tmp, it, or retval, have a good reason for doing so.
- Having different formats for different entities is like a form of syntax highlighting.

### Names That Can’t Be Misconstrued

- The clearest way to name a limit is to put max_ or min_ in front of the thing being limited.
