# User Guide

# About HomeBoss

HomeBoss is a powerful desktop application designed specifically for **home-based business owners**, offering *
*efficient Customer and Delivery management solutions**. With HomeBoss, you can streamline your administrative processes
and elevate your overall business experience to new heights.

With our application, you will be able to:

* Keep track of the details of your Customers and Deliveries
* Search for a particular Customer / Delivery easily
* Organise your data in a neat and tidy manner by filtering and sorting to your needs </br>
  and much more!

HomeBoss's goal is to improve your efficiency and productivity in managing your Customers and Deliveries, all while
being incredibly simple to use.
HomeBoss is not only **tailored for fast typists**, leveraging a Command Line Interface (CLI) for **swift command
execution**, but it also features an elegant Graphical User Interface (GUI) that presents your data in a **more refined
and organized** fashion.

With HomeBoss, you can now **focus on what matters most: running your business.**

---

# Table of Contents

&nbsp;&nbsp;1\. [About HomeBoss](#about-homeboss)</br>
&nbsp;&nbsp;2\. [Table of Contents](#table-of-contents)</br>
&nbsp;&nbsp;3\. [About this User Guide](#about-this-user-guide)</br>
&nbsp;&nbsp;4\. [Navigating the User Guide](#navigating-the-user-guide)</br>
&nbsp;&nbsp;5\. [Getting Started](#getting-started)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.1. [Prerequisites](#prerequisites)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.2. [Installing HomeBoss](#installing-homeboss)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.3. [Understanding our layout](#understanding-our-layout)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.4. [HomeBoss's Command Format](#homeboss-s-command-format)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.5. [Registering and creating your first Delivery](#registering-and-creating-your-first-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.6. [Editing the data file](#editing-the-data-file) </br>
&nbsp;&nbsp;6\. [Feature Summary](#feature-summary)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.1. [User features summary](#user-features-summary)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.2. [Customer features summary](#customer-features-summary)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.3. [Delivery features summary](#delivery-features-summary)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.4. [Miscellaneous features summary](#miscellaneous-features-summary)</br>
&nbsp;&nbsp;7\. [Features](#features)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1. [User](#user)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.1. [Register](#register)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.2. [Login](#login)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.3. [Update Account details](#update-account-details)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.4. [Logout](#logout)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.5. [Recover Account](#recover-account)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.1.6. [Delete Account](#delete-account)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2. [Customer](#customer)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.1. [Add a Customer](#add-a-customer)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.2. [View details of a Customer](#view-details-of-a-customer)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.3. [View a list of Customers](#view-a-list-of-customers)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.4. [Find Customers](#find-customers)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.5. [Update details of a Customer](#update-details-of-a-customer)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.2.6. [Delete a Customer](#delete-a-customer)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3. [Delivery](#delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.1. [Add a Delivery](#add-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.2. [View details of a Delivery](#view-details-of-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.3. [View a list of Deliveries](#view-a-list-of-deliveries)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.4. [Find Deliveries](#find-deliveries)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.5. [Update details of a Delivery](#update-details-of-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.6. [Update status of a Delivery](#update-status-of-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.7. [Create a note for a Delivery](#create-a-note-for-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.3.8. [Delete a Delivery](#delete-a-delivery)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.4. [Miscellaneous](#miscellaneous)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.4.1. [Help](#help)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.4.2. [Exit](#exit)</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;7.4.3. [Clear](#clear)</br>
&nbsp;&nbsp;8\. [FAQ](#faq)</br>
&nbsp;&nbsp;9\. [Command Summary](#command-summary)</br>

---

# About this User Guide

This user guide provides you with everything that you need to know when using HomeBoss.

If you are new to HomeBoss, head over to the [Getting Started](#getting-started) section for a quick overview
on how to get started.

Additionally, this user guide provides you with detailed explanations of all the features available
in the [Features](#features) section.

For experienced users, you may refer to the [Command Summary](#command-summary) section for a quick summary of all the
commands available in HomeBoss.

If you would like to learn about the technical aspects of HomeBoss,
you may refer to the [Developer Guide](./DeveloperGuide.md).

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

---

# Navigating the User Guide

**Warning Box**

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning:** Provides you with important information to take note of to avoid any unintended effects!

</box>

<br/>

**Note Box**

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** Provides you with information that is useful to know.

</box>

<br/>

**Tip Box**

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:** Provides you with information that can help enhance your user experience but is not necessary to know.

</box>

<br/>

**Parameter Box**

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):** Parameters are inputs that you can customise to your needs. They are typically preceded by a prefix.
If you're unsure what a prefix is, you can refer to the [Command Format](#homeboss-s-command-format) section.

</box>

<br/>

**Links**

* Words in blue are [links](#navigating-the-user-guide) that you can click on to navigate to the relevant section.

* A [&uarr; Back to Table of Contents](#table-of-contents) link is available at the end of every section
  for you to return to the Table of Contents, so that you can access another section from there easily.

---

# Getting Started

## Prerequisites

Ensure that you have `Java 11` or above installed on your computer. If you are unsure how to do so you may view
this
helpful [guide](https://www.java.com/en/download/help/version_manual.html).

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip**: You may find the following links helpful should you need to install `Java 11` :

1. [Java 11 Release](https://www.oracle.com/java/technologies/downloads/#java11)
2. [Java 11 Installation Guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

## </br>Installing HomeBoss

1. You may download the latest release of **HomeBoss**
   from [here](https://github.com/AY2324S1-CS2103T-T13-3/tp/releases/latest).
   (Please download the file named `HomeBoss.jar`)
2. Move the downloaded `HomeBoss.jar` file into the folder that you want to use as the home folder for your
   application.

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning**: The folder that you would like to use as the home folder for HomeBoss must be empty and should not contain
any other
files / folders before the application is launched for the first time.

</box>

3. Run `HomeBoss.jar`. If you are unsure of how to run a `.jar` file, you may refer to this helpful
   [guide](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Run-JAR-file-example-windows-linux-ubuntu).
4. If everything went well, you will be greeted by a window similar to the one shown below.
5. If you are a first time user, we highly recommend reading the following sections to familiarise yourself with
   HomeBoss's layout and commands:
    * [Understanding our layout](#understanding-our-layout) <br/>
    * [HomeBoss's Command Format](#homeboss-s-command-format) <br/>
    * [Registering and creating your first Delivery](#registering-and-creating-your-first-delivery)

![HomeBoss Start Page](images/getting-started/register.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

## <br/>Understanding our layout

Do not worry if the layout looks unfamiliar at first glance. Let's go through a quick run down of HomeBoss's layout
so that you can familiarise yourself with our various components.

![HomeBoss Home Page](images/getting-started/homepage_annotated.png)

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**: The above image is an example with sample data and may not be what you see when you launch
the application for the first time.

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### <br/>Command Box

![HomeBoss Command Box](images/getting-started/command_box.png){style="display: block; margin: 0 auto;"}

The Command Box is where you will be interacting with HomeBoss, it is the place where all commands
will be entered. Try typing the `help`{.swift} command and hitting enter!

### </br>Command Result / Feedback

![Command Result Feedback](images/getting-started/help_feedback.png){style="display: block; margin: 0 auto;"}

The Command Result / Feedback Box is where HomeBoss will provide you the results or feedback about
the commands you entered. For example, if you tried typing the `help`{.swift} command earlier, you should see
the above feedback in your application window, together with a Help window popup.
You can use the scroll bar on the right to scroll through a long result or feedback!

### </br>Customer / Delivery List

<table class="images" style="border:0px solid white; width:100%; text-align: center">
    <tr style="width:100%;border: 0;">
        <td>
            <img src="images/getting-started/customer_list_cropped.png" style="width: 80%;display: block; margin: 0 auto;">
        </td>
        <td>
            <img src="images/getting-started/delivery_list_cropped.png" style="width: 80%;display: block; margin: 0 auto;">
        </td>
    </tr>
    <tr >
        <td style="padding-top: 10px; padding-bottom: 20px"><i>List of Customers</i></td>
        <td style="padding-top: 10px; padding-bottom: 20px"><i>List of Deliveries</i></td>
    </tr>
</table>

The Customer / Delivery list panel is where you will be able to find the Customers and Deliveries that you have added to
HomeBoss. You can use the scroll bar on the right to scroll through your Customers or Deliveries.

<br/>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**:

* The `ID`{.swift} that appears next to each name is a unique identifier given to all added Customers and
  Deliveries separately, and is required to perform many of HomeBoss's commands.
  More details of these commands can be found in [Features](#features). <br/><br/>
  
* The `ID`{.swift} generated are guaranteed to be unique but may not be consecutive and may not start from 1.<br/>

![Command Result FeedBack](images/getting-started/delivery_card.png){style="display: block; margin: 0 auto;"}

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

## </br>HomeBoss's Command Format

Commands are your main form of interaction with HomeBoss, and is the way to carry out various operations in HomeBoss.
Don't worry if the commands seem daunting, the HomeBoss team has carefully crafted the commands to be as intuitive
and simple as possible. After learning the commands, you'll be faster than ever!

Every command has a **Command Phrase** and possibly one or more **Parameters**
(inputs to customise the command to your needs) which is typically preceded by a **Prefix**
(special markers for HomeBoss to understand your inputs)

Take the [Add a Delivery](#add-a-delivery) command for example:

```swift
delivery add DELIVERY_NAME --customer CUSTOMER_ID --date DELIVERY_DATE
```

* The **Command Phrase** is `delivery add`{.swift}.
* The **Parameters** are the words in uppercase, for example `DELIVERY_NAME`{.swift} or `CUSTOMER_ID`{.swift}.
* The **Prefixes** are special keywords that are preceded by `--`{.swift}, such as `--customer`{.swift} and
  `--date`{.swift}.

<br />

However, not all commands have **Prefixes**. Take the
[Update status of a Delivery](#update-status-of-a-delivery) command as example:

```swift
delivery status STATUS CUSTOMER_ID
```

* The **Command Phrase** is `delivery status`{.swift}.
* The **Parameters** are `STATUS`{.swift} and `CUSTOMER_ID`{.swift}.
* It has no **Prefixes**.

<br />

Not all commands have **Parameters** as well. Take the
[Logout](#logout) command as example:

```swift
logout
```

* The **Command Phrase** is `logout`{.swift}.
* It has no **Parameters**.

<br/>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**:

* Command Phrase are keywords or short phrases that are unique to each Command.
* Words that are in uppercase are parameters that are to be supplied by you.
* Parameters that are preceded by a prefix must be supplied after that prefix, e.g. `--name DELIVERY_NAME`{.swift}
  must be given as, for example, `--name furniture` and not `furniture` or `--name`.
* Items that are placed in square brackets (`[]`{.swift}) are optional, e.g. `DELIVERY_ID [--name DELIVERY_NAME]`
  {.swift} can either be
  `1` or `1 --name furniture`.
* However, items that are grouped together in square brackets (`[]`{.swift}) must all be provided together,
  e.g. for `[--password PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift} both `PASSWORD`{.swift} and
  `CONFIRM_PASSWORD`{.swift} must be provided.
* Extraneous parameters for commands that do not have parameters (e.g. `customer list`{.swift} or `clear`{.swift}) will
  be ignored, e.g. `customer list 123`.
* Parameters that have `...`{.swift} as a postfix can be given one or more times, e.g. `KEYWORDS...`{.swift} can be
  given as `Keyword` or `Keyword AnotherKeyword`.
* **Command Phrase** and **Prefixes** are **case-sensitive**! e.g. `clear`{.swift} is a valid command word but
  `customer LIST` is not a valid command word.
* Leading and trailing whitespaces of parameters will be trimmed, i.e., spaces added between `--password`{.swift}
  and `PASSWORD`{.swift} in `--password PASSWORD`{.swift} will be removed.

  </box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

## </br>Registering and creating your first Delivery

At this juncture, you should already have a good understanding of the basic mechanisms of HomeBoss. Let's now get you
started on HomeBoss
by creating your first Delivery.

1. Let's first register for HomeBoss using the `register`{.swift} command. Suppose you want to register an account
   with the following details:

* `USERNAME`{.swift} : yourUsername
* `PASSWORD`{.swift} : AlexIsGreat
* `CONFIRM_PASSWORD`{.swift} : AlexIsGreat
* `SECRET_QUESTION`{.swift} : First Pet Name?
* `ANSWER`{.swift} : Koko

Type `register --user yourUsername --password AlexIsGreat --confirmPass AlexIsGreat --secretQn First Pet Name?
--answer Koko` into the Command Box and hit enter. More details on the `register` command can be
found [here](#register).

<br/>


<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**: Only 1 account can be registered in HomeBoss at any one time.

</box>

<br/>

2. After registering for an account, you will be greeted by HomeBoss's home page. It's empty at the moment, so let's
   populate it with some data.

3. Let's now add a new Customer, `Gabriel`, to HomeBoss' Customer database using the `customer add`{.swift} command.

* `NAME`{.swift} : Gabriel
* `PHONE`{.swift} : 87654321
* `EMAIL`{.swift} : gabrielrocks@gmail.com
* `ADDRESS`{.swift} : RVRC Block B

4. Type `customer add --name Gabriel --phone 87654321 --email gabrielrocks@gmail.com --address RVRC Block B`
   into the Command Box and hit enter.

5. A new Customer called `Gabriel` will be added, and you will see the Customer's details reflected in the Customer List
   panel, similar to the image shown below.

![CustomerAdded](images/getting-started/customer_add.png){style="display: block; margin: 0 auto;"}

1. Now, let's add a new Delivery for `Gabriel` into HomeBoss' Delivery database. For this example, `Gabriel` has just
   ordered a `MacBook Pro` from you, and the delivery date that you expect to deliver to him by is `2023-12-03`.

* `DELIVERY_NAME`{.swift} : MacBook Pro
* `DELIVERY_DATE`{.swift} : 2023-12-03

7. Type `delivery add MacBook Pro --customer CUSTOMER_ID --date 2023-12-03`, replacing `CUSTOMER_ID`{.swift}

   with Gabriel's ID, and hit enter.

8. A new Delivery should have been added for `Gabriel`, you should see a new Delivery in the Delivery List panel,
   similar to the image shown below.

![DeliveryAdded](images/getting-started/delivery_add.png){style="display: block; margin: 0 auto;"}

Congratulations! You have just successfully added your first Customer and Delivery!

HomeBoss is packed with many more features to help you manage your Customers and Deliveries.
Check them out under the [Features](#features) section to learn more!

## </br>Editing the data file

HomeBoss stores your data in a JSON file automatically `[JAR file location]/data/addressbook.json`. Advanced
users are welcome to update data directly by editing the data file.

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning:** If the files are edited and contain invalid data as a result, HomeBoss will discard the entire data file
and start with an empty data file during the next run. (If the Customer data file contains invalid data, the entire
Delivery data file will also be deleted. However, if the Delivery data file contains invalid data, the Customer data
file will remain unaffected.)

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

---

# Feature Summary

Here is a brief introduction to all the features in HomeBoss. For more detailed information, please
refer to the [Features](#features) section.

## </br>User features summary

_These are features for managing your user account in HomeBoss._

- `register` - Registers a new user account to use HomeBoss.
- `login` - Logs in to your user account.
- `update` - Updates your user account details.
- `recover account` - Recovers your user account.
- `logout` - Logs out of your user account.
- `delete account` - Deletes your user account.

## </br>Customer features summary

_These are features for managing your Customers in HomeBoss._

- `customer add` - Adds a Customer to the address book.
- `customer view` - Shows the details of the specified Customer.
- `customer list` - Lists all Customers in the address book.
- `customer find` - Finds Customers whose names contain any of the given keywords.
- `customer edit` - Updates the details of an existing Customer in the address book.
- `customer delete` - Deletes the specified Customer from the address book.

## </br>Delivery features summary

_These are features for managing your Deliveries in HomeBoss._

- `delivery add` - Adds a Delivery to the delivery book.
- `delivery view` - Shows the details of the specified Delivery.
- `delivery list` - Lists all Deliveries in the delivery book.
- `delivery find` - Finds Deliveries whose names contain any of the given keywords.
- `delivery edit` - Updates the details of an existing Delivery in the delivery book.
- `delivery status` - Changes the status of a specified Delivery.
- `delivery note` - Creates a note for a specified Delivery.
- `delivery delete` - Deletes the specified Delivery from the delivery book.

## </br>Miscellaneous features summary

_These are general features in HomeBoss._

- `exit` - Exits the program.
- `help` - Shows a list of commands and their usage.
- `clear` - Clears both Customer and Delivery database.


<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

---

# Features

## <span style="text-decoration:underline;"><strong>User</strong></span>

### Register

You can register for a new user account with HomeBoss by calling this command.

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** Only one account can be registered at any one time.

</box>

</br>**Format:**


 
`register --user USERNAME --password PASSWORD --confirmPass CONFIRM_PASSWORD --secretQn SECRET_QUESTION --answer ANSWER`
{.swift}


<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `USERNAME`{.swift} must be alphanumeric.<br/><br/>
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.<br/><br/>
* `CONFIRM_PASSWORD`{.swift} must be the same as `PASSWORD`{.swift}.<br/><br/>
* `SECRET_QUESTION`{.swift} can include any characters but must not be empty.<br/><br/>
* `ANSWER`{.swift} can include any characters but must not be empty.<br/><br/>
* `SECRET_QUESTION`{.swift} and `ANSWER`{.swift} will be used for account recovery.<br/>

</box>

</br>**Example:**

* `register --user yourUsername --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name? --answer Koko`</br>

  Registers a new user account with the username `yourUsername`, password `GabrielIsGreat`, secret
  question `First Pet Name?` and answer `Koko`.

<br/>

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:** Since only one account can be registered at any one time, if you have already registered an account, you will
not be able to register another account. If you must, you can delete your current account by using the `delete account`
{.swift} command [here](#delete-account) before registering a new account.
</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### </br></br>Login

You can log in to your account to access your Customer and Delivery data by calling this command.

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** You need to already have an account registered with HomeBoss.

</box>


</br>**Format:**

`login --user USERNAME --password PASSWORD`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `USERNAME`{.swift} must be alphanumeric.<br/><br/>
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.<br/>

</box>

</br>**Example:**

* `login --user yourUsername --password GabrielIsGreat` </br>
  Logs in to the user account with the username `yourUsername` and password `GabrielIsGreat`.

![](images/ug/login_data.png)
</br>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Update Account details

This command allows you to update your account details, thus enabling greater security and personalisation.

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** The details will be updated without checking against the current details.

</box>

</br>**Format:**

`update [--user USERNAME] [--password PASSWORD --confirmPass CONFIRM_PASSWORD]
[--secretQn SECRET_QUESTION --answer ANSWER]`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* At least one of the optional fields must be provided.<br/><br/>
* `USERNAME`{.swift} must be alphanumeric.<br/><br/>
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.<br/><br/>
* `CONFIRM_PASSWORD`{.swift} must be the same as `PASSWORD`{.swift}.<br/><br/>
* `SECRET_QUESTION`{.swift} can take any characters.<br/><br/>
* `ANSWER`{.swift} can take any characters.<br/>

</box>

</br>**Example:**

* `update --user GabrielV2 --password GabrielIsBest --confirmPass GabrielIsBest
  --secretQn Favourite Pet --answer BoBo` </br>
  Updates the username to `GabrielV2`, password to `GabrielIsBest`,
  secret question to `Favourite Pet` and answer to `BoBo`.

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Logout

You can log out of your account to keep your data secure at the end of the day by calling this command.

<br/>

**Format:** `logout`{.swift}

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### </br></br>Recover Account

HomeBoss makes it easy for you to recover your account if you forget your password. You only need the answer to the
secret question (set during account registration) to reset your password and thus regain access to your account.

</br>**Format:**

`recover account [--answer ANSWER --password NEW_PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* If `ANSWER`{.swift} is provided, `NEW_PASSWORD`{.swift} and `CONFIRM_PASSWORD`{.swift} must also be provided and vice
  versa.<br/><br/>
* `ANSWER`{.swift} can take any characters.<br/><br/>
* `NEW_PASSWORD`{.swift} must be at least 8 alphanumeric characters long.<br/><br/>
* `CONFIRM_PASSWORD`{.swift} must be the same as `NEW_PASSWORD`{.swift}.<br/>

</box>

</br>**Examples:**

* `recover account`{.swift} </br>
  Displays your account's secret question for account recovery.

* `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123` </br>
  Recovers the account with the answer `Koko` and sets the new password to `NewPassword123`.

</br>**Without optional fields (i.e., `recover account`):**
![](images/user/userRecoverAccount_after_secretQn.png)

</br>**With optional fields (i.e.,
`recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`):**
![](images/user/userRecoverAccount_after_answer.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### </br></br>Delete Account

If you simply want to erase all your data from HomeBoss, you can delete your account by calling this command.

</br>**Format:** `delete account`{.swift}

<br />

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:** This command should be considered as a last resort, to be used if you forget both your password and your secret
answer.
However, if you've forgotten your password but recall the answer to your secret question, you can initiate the account
recovery process by using the `recover account`{.swift} command [here](#recover-account).

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

## </br><span style="text-decoration:underline;"><strong>Customer</strong></span>

### Add a Customer

You can add a new Customer's details into HomeBoss with this command.

<br />
<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* Customers consist of a name, a phone number, an email, and an address.<br/><br/>
* HomeBoss does not allow you to add Customers with the same phone number.<br/><br/>
* A unique Customer ID (may not be consecutive) will be assigned to the Customer.<br/>

</box>

</br>**Format:**

`customer add --name NAME --phone PHONE_NUMBER --email EMAIL --address ADDRESS`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `NAME`{.swift} must be alphanumeric and can contain spaces.<br/><br/>
* `PHONE_NUMBER`{.swift} must be exactly 8 digits.<br/><br/>
* `EMAIL`{.swift} must follow the `local@domain`{.swift} format.<br/><br/>
* `ADDRESS`{.swift} can take any characters.<br/>

</box>

</br>**Example:**

* `customer add --name Gabriel --phone 87654321 --email gabrielrocks@gmail.com --address RVRC Block B` </br>
  Adds a Customer with the name `Gabriel`, phone number `87654321`,
  email `Gabrielrocks@gmail.com`and address `RVRC Block B`.

![](images/ug/customer_add.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>View details of a Customer

You can view the details of a Customer by calling this command. The data displayed includes the Customer's name, phone
number, email and address.

</br>**Format:**

`customer view CUSTOMER_ID`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):** `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.

  </box>

<br>**Example:**

* `customer view 1` </br>
  Displays the details of Customer with ID `1`.

![](images/ug/customer_view.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>View a list of Customers

This command allows you to view a list of all the Customers that you have added to HomeBoss.

<br />

**Format:** `customer list`{.swift}

<br />

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:** You can frequently use this command together with `delivery list`{.swift} to switch between the two lists.

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Find Customers

If you want to get the details of a Customer, but do not remember the Customer's full name or the Customer's ID,
you can find the Customer with this command. It finds Customers whose names has words that exactly match any of the
given keywords.

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* You must provide at least one keyword to search for the Customer.<br/><br/>
* You can optionally provide additional keywords. Customer names that matches any of the given keywords will be
  displayed. For example, the keywords `Alex` and `Tan` in `Alex Tan` will display `Alex Wong`, `Alex Tan`,
  and `Tan Ah Meng`.<br/><br/>
* The keyword must exactly match any word in the Customer's name. For example the keyword `Alex` will match `Alex` but
  not `Alexander`.<br/><br/>
* The search is not case sensitive.<br/>

</box>

</br>**Format:**

`customer find KEYWORD [MORE_KEYWORDS...]`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* Each `KEYWORD`{.swift} must be alphanumeric and cannot contain spaces.<br/><br/>
* If there's more than one `KEYWORD`{.swift}, there has to be a space between each `KEYWORD`{.swift}.<br/>

</box>

</br>**Example:**

* `customer find Julius Yang`</br>
  Finds Customers whose names have words that exactly match either `Julius` or `Yang`.

![](images/ug/find.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Update details of a Customer

This command is useful for updating the details of a Customer, such as due to a change in particulars or in the event
that you keyed in the Customer's details wrongly.

</br>**Format:**

`customer edit CUSTOMER_ID [--name NAME] [--phone PHONE_NUMBER] [--email EMAIL] [--address ADDRESS]`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* At least one of the optional fields must be provided. <br/><br/>
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer's ID.<br/><br/>
* `NAME`{.swift} must be alphanumeric and can contain spaces.<br/><br/>
* `PHONE_NUMBER`{.swift} must be exactly 8 digits.<br/><br/>
* `EMAIL`{.swift} must follow the `local@domain`{.swift} format.<br/><br/>
* `ADDRESS`{.swift} can take any characters.<br/>

</box>

</br>**Examples:**

* `customer edit 1 --name Gabriel --phone 98131051` </br>
  Edits the name of the Customer, with Customer ID of `1`, to `Gabriel` and his phone to `98131051`.
* `customer edit 2 --name Joe --email yangyang@gmail.com --address Block 10 Tampines Road` </br>
  Edits the name of the Customer, with Customer ID of `2`, to `Joe`, his email to `yangyang@gmail.com` and his address
  to
  `Block 10 Tampines Road`.

<br/>


[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### </br></br>Delete a Customer

You can delete any Customer who no longer require your services by calling this command.

<br/>


<box background-color="#f2dede" border-color="#ebccd1" type="warning" icon=":exclamation:">

**Warning:** Be careful! All Deliveries associated with the Customer will also be deleted. You won't be able to undo
this deletion!
</box>

</br>**Format:**

`customer delete CUSTOMER_ID`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):** `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.

</box>

</br>**Example:**

* `customer delete 1` </br>
  Deletes the Customer with ID `1`.

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

## </br><span style="text-decoration:underline;"><strong>Delivery</strong></span>

### Add a Delivery

By using this command, you can incorporate Deliveries associated with your Customers into HomeBoss. Adding a Delivery is
the initial step that later enables you to monitor the Delivery's status and other relevant details with our suite of
Delivery-related commands.

<br/>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* Deliveries consist of a Delivery name, a Customer ID, an order date, an expected Delivery date, a Delivery status and
  an address to ship to. </br></br>
* You don't have to fill in order date, Delivery status and address. Instead they will be initialised with these values:
    * Order date: Today's date
    * Delivery status: `CREATED`
    * Address: Customer's address
      <br/></br>
* Delivery Status can be one of `CREATED`, `SHIPPED`, `COMPLETED`, `CANCELLED`. <br/>

</box>

</br>**Format:**

`delivery add DELIVERY_NAME --customer CUSTOMER_ID --date EXPECTED_DELIVERY_DATE`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `DELIVERY_NAME`{.swift} must be alphanumeric and can contain spaces.<br/><br/>
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.<br/><br/>
* `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format.<br/>

</box>

</br>**Example:**

* `delivery add Chocolate Cake --customer 1 --date 2023-12-12` </br>

  Adds a delivery with the name `Chocolate Cake` for the customer with ID `1`. The expected delivery date is set
  to `2023-12-12`, the order date is automatically set to today's date, the delivery status is initialised to `CREATED`,
  and the delivery address is the same as the customer's address.

![](images/ug/delivery_add.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>View details of a Delivery

You can view the details of a Delivery by calling this command. The data displayed includes the Delivery's name, status,
Customer, Customer ID, address, order date, expected delivery date and notes (if any).

</br>**Format:**

`delivery view DELIVERY_ID`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):** `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.

</box>

</br>**Example:**

* `delivery view 1` </br>
  Displays the details of the Delivery with Delivery ID of `1`.

![](images/ug/delivery_view.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>View a list of Deliveries

This command allows you to view a list of all the Deliveries that you have added to HomeBoss.

<br/>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** This is a more powerful command than `customer list`{.swift}, as it allows you to filter the list of
Deliveries by status, customer ID and/or expected delivery date. This command also allows you to sort the list of
Deliveries by expected delivery date in either ascending or descending order. By default, the list of Deliveries will be
sorted by expected delivery date in descending order (latest first).

</box>

</br>**Format:**

`delivery list [--status STATUS] [--customer CUSTOMER_ID] [--date EXPECTED_DELIVERY_DATE]  [--sort SORT]`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `STATUS`{.swift} accepts the following values: `CREATED`/`SHIPPED`/`COMPLETED`/`CANCELLED`.
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
* `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format OR
  `TODAY`{.swift} for today’s date.
* `SORT`{.swift} accepts the following values: `ASC`/`DESC`.

  </box>

</br>**Examples:**

* `delivery list --status CREATED --customer 1 --sort DESC`</br>
  Lists all Deliveries with status `CREATED` for Customer with ID `1` with expected Delivery date in
  descending order.

* `delivery list --status SHIPPED --date TODAY`{.swift} </br>
  Lists all Deliveries with status `SHIPPED` for all Customers and expected Delivery date of `TODAY`.

<br />

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:**
You may combine any of the filters and sort options to get the list of Deliveries that you want.
</box>

![](images/ug/delivery_list.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Find Deliveries

If you cannot remember the full name of a Delivery, you can find the Delivery with this command. It finds Deliveries
whose names has words that exactly match any of the given keywords.

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* You must provide at least one alphanumeric keyword to search for the Delivery.<br/><br/>
* You can optionally provide additional keywords. Deliveries that has names matching any of the given keywords will be
  displayed. For example, the keywords `Chocolate` and `Bun` in `Chocolate Bun` will
  display `Chocolate Cake`, `Chocolate Bun`, and `Strawberry Bun`.<br/><br/>
* The keyword must exactly match any word in the delivery name. For example, the keyword `Straw` will match `Straw` but
  not `Strawberry`.<br/><br/>
* The search is not case sensitive.<br/>

</box>

</br>**Format:**

`delivery find KEYWORD [MORE_KEYWORDS...]`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* Each `KEYWORD`{.swift} must be alphanumeric and cannot contain spaces.<br/><br/>
* If there's more than one `KEYWORD`{.swift}, there has to be a space between each `KEYWORD`{.swift}.<br/>

</box>

</br>**Example:**

* `delivery find Gambes Banana` </br>
  Finds all Deliveries whose name has words that exactly match `Gambes` or `Banana`.


![](images/ug/delivery_find.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

#### </br></br>Update details of a Delivery

This command is useful for updating the details of a Delivery, such as due to a change in the details of a Delivery or
in the event that you keyed in the Delivery's details wrongly.

<br />

<box background-color="#f2dede" border-color="#ebccd1" type="warning" icon=":exclamation:">

**Warning:** Be careful! You won't be able to undo this edit action!

</box>

</br>**Format:**

`delivery edit DELIVERY_ID [--name DELIVERY_NAME] [--customer CUSTOMER_ID] [--date EXPECTED_DELIVERY_DATE][--status STATUS] [--note NOTE]`
{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* At least one of the optional fields must be provided.<br/><br/>
* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.<br/><br/>
* `DELIVERY_NAME`{.swift} must be alphanumeric and can contain spaces.<br/><br/>
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.<br/><br/>
* `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format.<br/><br/>
* `STATUS`{.swift} accepts the following values: `CREATED`/`SHIPPED`/`COMPLETED`/`CANCELLED`.<br/><br/>
* `NOTE`{.swift} must be alphanumeric and can contain spaces.<br/>

</box>

</br>**Examples:**

* `delivery edit 1 --status CANCELLED --note Sudden overseas business trip to attend to` </br>
  Edits the Delivery's status of the Delivery, with Delivery ID of `1`, to `CANCELLED` and edits the note of the
  Delivery to `Sudden overseas business trip to attend to`. If this Delivery does not already have a note, a note will
  be created for it.
* `delivery edit 2 --name Vanilla Cake --customer 3` </br>
  Edits the name of the Delivery, with Delivery ID of `2`, to `Vanilla Cake` and edits the Customer of the
  Delivery to the Customer with ID of `3`.


<br/>

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:**

* If you only want to update the Delivery's status, simply use `delivery status`{.swift} instead. </br><br/>
* If you only want to update the Delivery's note, simply use `delivery note`{.swift} instead.<br/>
  </box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Update status of a Delivery

An order can have one of the following statuses: CREATED, SHIPPED, COMPLETED or CANCELLED. This command allows you to
update the status of a Delivery to any of the aforementioned statuses, according to the progress of the Delivery for
your easy tracking.

</br>**Format:** `delivery status DELIVERY_ID STATUS`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.
 
* `STATUS`{.swift} accepts the following values: `CREATED`/`SHIPPED`/`COMPLETED`/`CANCELLED`.


  </box>

</br>**Examples:**

* `delivery status 1 CANCELLED` </br>
  Updates the status of Delivery with ID `1` to `CANCELLED`.
* `delivery status 2 SHIPPED` </br>
  Updates the status of Delivery with ID `2` to `SHIPPED`.

<br/>

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:** Delivery status is not case sensitive. You can type `created` instead of `CREATED` and it will still work.

</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Create a note for a Delivery

If you would like to create a note about a specific delivery, this command allows you to do so as a shortcut, as opposed
to using the lengthier `delivery edit`{.swift} command found [here](#update-details-of-a-delivery).

<br />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** If the Delivery already has a note, the previous note will be overwritten by the new given note.

</box>

</br>**Format:**

`delivery note DELIVERY_ID --note NOTE`{.swift}

<br/>

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):**

* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery's ID.<br/><br/>
* `NOTE`{.swift} must be alphanumeric and can contain spaces.<br/>

</box>

</br>**Example:**

* `delivery note 1 --note By FedEx` </br>
  Creates a new note `By FedEx` for the Delivery with ID `1`{.swift}.

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Delete a Delivery

If you feel that a Delivery is no longer relevant (i.e., cancelled or completed), you can delete it from HomeBoss using
this command.

<br/>

<box background-color="#f2dede" border-color="#ebccd1" type="warning" icon=":exclamation:">

**Warning:** Be careful! This action is irreversible. Once deleted, the delivery cannot be recovered.

</box>

</br>**Format:**

`delivery delete DELIVERY_ID`{.swift}

<br />

<box type="info" background-color="#fcf8e3" border-color="#fcf8ff" icon=":gear:">

**Parameter(s):** `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.

</box>

</br>**Example:**

* `delivery delete 1` </br>
  Deletes the Delivery with ID of `1` from the Delivery database.

  <br/>

[&uarr; Back to Table of Contents](#table-of-contents)

## </br><span style="text-decoration:underline;"><strong>Miscellaneous</strong></span>

<br/>

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">


**Tip:** The commands under this section can be used at any time, regardless of whether you are logged in or not.

</box>

<br/>

### Help

This command allows you view a summary of the commands available, the format of the commands, and a link to this user
guide.

<br/>

**Format:** `help`{.swift}

![](images/ug/help.png)

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Exit

You can exit the program by calling this command.

**Format:** `exit`{.swift}

<br/>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** If you are logged in, this command will automatically log you out of your account and close the application.
</box>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

### </br></br>Clear

In the event that you want to perform a complete reset of HomeBoss' Customer and Delivery database, you can easily clear
all your Customer and Delivery data by calling this command.

<br/>

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning:** Be careful, this action is irreversible! All your Customer and Delivery data will be deleted permanently.
Proceed with caution!

</box>

**<br/>Format:** `clear`{.swift}

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

---

# FAQ

<br/>

**Q: Where do I get support for HomeBoss?**

A: You can raise an issue on our [GitHub repository](https://github.com/AY2324S1-CS2103T-T13-3/tp/issues)<br/><br/>

**Q: How do I report a bug?**

A: You can raise a bug report on our [GitHub repository](https://github.com/AY2324S1-CS2103T-T13-3/tp/issues)<br/><br/>

**Q: How do I import my data from another software?**

A: You can import your data by converting your data into .json in the format of the Customer and Delivery data in the
data folder.<br/><br/>

**Q: How do I export my data to another software?**

A: It is currently not possible to export your data to another software.<br/><br/>

**Q: Why is there an error when I input non-English characters?**

A: HomeBoss only supports English characters.<br/><br/>

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

---

# Command Summary

## User

| Command  | Format                                                                                                                               | Examples                                                                                                                  |
|----------|--------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| Register | `register --user USERNAME --password PASSWORD --confirmPass CONFIRM_PASSWORD --secretQn SECRET_QUESTION --answer ANSWER`{.swift}     | `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name? --answer Koko` |
| Login    | `login --user USERNAME --password PASSWORD`{.swift}                                                                                  | `login --user Gabriel --password GabrielIsGreat`                                                                          |
| Update   | `update [--user USERNAME] [--password PASSWORD --confirmPass CONFIRM_PASSWORD] [--secretQn SECRET_QUESTION --answer ANSWER]`{.swift} | `update --user GabrielV2 --password GabrielIsBest --confirmPass GabrielIsBest --secretQn Favourite Pet --answer BoBo`     |
| Recover  | `recover account [--answer ANSWER --password NEW_PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift}                                   | `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`                                    |
| Logout   | `logout`{.swift}                                                                                                                     | `logout`                                                                                                                  |
| Delete   | `delete account`{.swift}                                                                                                             | `delete account`                                                                                                          |

<br>

## Customer

| Command | Format                                                                                                       | Examples                                                                                                         |
|---------|--------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| Add     | `customer add --name NAME --phone PHONE_NUMBER --email EMAIL --address ADDRESS`{.swift}                      | `customer add --name Gabriel --phone 87654321 --email Gabrielrocks@gmail.com --address RVRC Block B`             |
| View    | `customer view CUSTOMER_ID`{.swift}                                                                          | `customer view 1`                                                                                                |
| List    | `customer list`{.swift}                                                                                      | `customer list`                                                                                                  |
| Find    | `customer find KEYWORD [MORE_KEYWORDS]`{.swift}                                                              | `customer find Julius Yang`                                                                                      |
| Edit    | `customer edit CUSTOMER_ID [--name NAME] [--phone PHONE_NUMBER] [--email EMAIL] [--address ADDRESS]`{.swift} | `customer edit 1 --name Gabriel --phone 97659320 --email Gabrielrock@gmail.com --address Block 10 Tampines Road` |
| Delete  | `customer delete CUSTOMER_ID`{.swift}                                                                        | `customer delete 1`                                                                                              |

<br>

## Delivery

| Command | Format                                                                                                                                     | Examples                                                                                                                   |
|---------|--------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| Add     | `delivery add DELIVERY_NAME --customer CUSTOMER_ID --date DELIVERY_DATE`{.swift}                                                           | `delivery add furniture --customer 5 --date 2023-12-03`                                                                    |
| View    | `delivery view DELIVERY_ID`{.swift}                                                                                                        | `delivery view 1`                                                                                                          |
| List    | `delivery list [--status STATUS] [--customer CUSTOMER_ID] [--date DELIVERY_DATE] [--sort SORT]`{.swift}                                    | `delivery list --status created --customer 1 --date 2023-12-12 --sort desc`                                                |
| Find    | `delivery find KEYWORD [MORE_KEYWORDS]`{.swift}                                                                                            | `delivery find Chocolate Bun`                                                                                              |
| Edit    | `delivery edit DELIVERY_ID [--name DELIVERY_NAME] [--customer CUSTOMER_ID] [--date DELIVERY_DATE] [--status STATUS] [--note NOTE]`{.swift} | `delivery edit 1 --name Chocolate Cake --customer 2 --date 2024-12-12 --status CANCELLED --note Customer changed his mind` |
| Status  | `delivery status DELIVERY_ID STATUS`{.swift}                                                                                               | `delivery status 2 completed`                                                                                              |
| Note    | `delivery note DELIVERY_ID --note NOTE`{.swift}                                                                                            | `delivery note 1 --note By FedEx`                                                                                          |
| Delete  | `delivery delete DELIVERY_ID`{.swift}                                                                                                      | `delivery delete 1`                                                                                                        |

<br>

## Miscellaneous

| Command | Format          | Examples |
|---------|-----------------|----------|
| Exit    | `exit`{.swift}  | `exit`   |
| Help    | `help`{.swift}  | `help`   |
| Clear   | `clear`{.swift} | `clear`  |

<br/>

[&uarr; Back to Table of Contents](#table-of-contents)

<br/>
