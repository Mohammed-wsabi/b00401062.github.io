# Operating Systems

An **operating system (OS)** is a disinterested intermediary between user applications and computer hardware. It is intangible but dominates the resources that come with a computer system, and manipulates how each program running on the machine interacts and shares the limited resources. No other words than *monarch* can better depict the role of the OS. Despite its essence being a software, its privilege is utterly insuperable since it is the only software that has direct control over the resources. The OS is also responsible for the integrity of the programs reliant on it and deter the infringement from extraneous instructions unless directed otherwise. Certain core instructions are designed to be impregnable that even the most advanced hacking techniques dims and bows.

The OS may come with a graphical user interface or not, but **kernel**, the heart that confers its authority, is consistently existent. Its architecture varies based on the extent of its codes inhabiting in **kernel space** or **user space**. On the two ends to the spectrum are *monolithic kernel* and *microkernel*. Any designs located in between are termed as *hybrid*.

**Microkernel** only retains components that must dwell in privileged mode such that its integrity is warranted. These components, although scientists have yet to reach a consensus, comprise (1) CPU clock, (2) process/thread management, and (3) synchronization. Since these components are so imperative that hardware also aids in their implementation. Microkernel advocates reusability of each component via extraordinary modularity. File service, device drivers, etc, are each wrapped into a standalone piece of the whole puzzle. Standardized APIs are provided to allow for putting all pieces together to build an application. The interaction of each module is coordinated and scrutinized by the kernel.

In contrast to microkernel, **monolithic kernel** implements all components in the kernel space by stacking them up in a hierarchical manner. A user application invokes a system call to use specific functionality provided by the kernel. In this way, the user application is believed to execute more efficiently since these codes run directly in the kernel space without the kernel's coordination.

![](https://upload.wikimedia.org/wikipedia/commons/6/67/OS-structure.svg)

The disputes over the design of the OSes contributes to the diversity of variants available on the market. The tremendous effort necessitated to bring into being a reliable and faithful system deters half-hearted laymen and shows off its taciturn intricacy and refinement to committed experts. The major OSes in the market are briefed as below.

## Unix

**Unix** is one the few OSes brought into beings in the early history of conputer science. Its development started at the Bell Labs in 1970s and has evolved tremendously since its inception. Ironically, Unix has never been made avilable on a modern computer. Instead, it is more of a spiritual standard followed by its descendent, often referred to as **Unix-like**. Its standardization is, to a large extent, maintained by [Single UNIX Specification (SUS)](http://www.unix.org). Some of the most well-known examples of so-called Unix-like OSes include [macOS](https://www.apple.com/macos) and [SunOS](https://www.oracle.com/solaris). The architecture of a Unix-like OS is up to respective implementation and the type of kernel it borrows from, e.g., macOS is based on [XNU](https://opensource.apple.com/source/xnu); SunOS is based on [BSD](https://www.bsd.org).

## Android

With the advent of personal mobile devices, **Android** was initially developed by [Google](https://www.google.com) in 2005 and unveiled in 2007. A newcomer as it is, **Android** boasts of the largest mobile OS plateform, inhabiting in over 3/4 of the mobile phones in the world as of August 2018. Its functionality is tailored to run on mobile devices that are allowed to connect to the Internet. The release of Android, although primarily licensed under the [Apache License](https://www.apache.org/licenses), is dominated by Google as a monopoly.

## Windows
