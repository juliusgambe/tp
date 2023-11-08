# User Guide

# About HomeBoss

HomeBoss is a desktop application for **home-based business owners** to **manage their Customers and Deliveries**.
HomeBoss streamlines the process of your operations, optimises your resources, and enhances your overall business
experience.

With our application, you will be able to:

* Keep track of the details of your Customers and Deliveries
* Search for a particular Customer/Delivery easily
* Organise your data in a neat and tidy manner by filtering and sorting to your needs </br>
  </br>
  and much more!

All these are done to improve your efficiency and productivity all while being incredibly simple to use.
HomeBoss is also **optimised for fast typist with the use of a Command Line Interface (CLI)**, allowing you to
execute commands quickly. On top of that, we utilise a *Graphical User Interface (GUI)* to let you view your
data in a neater and more organised manner.

Essentially, with HomeBoss, you can **focus on what matters most: running your business**.

---

# Table of Contents

* 1\. [About HomeBoss](#about-homeboss)
* 2\. Table of Contents
* 3\. [Introduction](#user-guide)
    * 3.1 [About this User Guide](#about-this-user-guide)
    * 3.2 [Navigating the User Guide](#navigating-the-user-guide)
* 4\. [Getting Started](#getting-started)
    * 4.1 [Prerequisites](#prerequisites)
    * 4.2 [Installing HomeBoss](#installing-homeboss)
    * 4.3 [Understanding our layout](#understanding-our-layout)
    * 4.4 [HomeBoss's Command Format](#homeboss-s-command-format)
    * 4.5 [Adding your first Delivery](#adding-your-first-delivery)
* 5\. [Features](#features)
    * 5.1 [User](#user)
        * 5.1.1 [Register](#register)
        * 5.1.2 [Login](#login)
        * 5.1.3 [Update Account details](#update-account-details)
        * 5.1.4 [Logout](#logout)
        * 5.1.5 [Recover Account](#recover-account)
        * 5.1.6 [Delete Account](#delete-account)
    * 5.2 [Customer](#customer)
        * 5.2.1 [Add Customer](#add-a-customer)
        * 5.2.2 [View details of a Customer](#view-details-of-a-delivery)
        * 5.2.3 [List Customers](#list-customers)
        * 5.2.4 [Find Customers](#find-customers)
        * 5.2.5 [Update Customer details](#update-customer-details)
        * 5.2.6 [Delete Customer](#delete-customer)
    * 5.3 [Delivery](#delivery)
        * 5.3.1 [Add Delivery](#add-delivery)
        * 5.3.2 [View details of a Delivery](#view-details-of-a-delivery)
        * 5.3.3 [List Deliveries](#view-a-list-of-deliveries)
        * 5.3.4 [Find Deliveries](#find-deliveries)
        * 5.3.5 [Update details of a Delivery](#update-delivery-details)
        * 5.3.6 [Update delivery status](#update-delivery-status)
        * 5.3.7 [Create a note for a Delivery](#create-a-note-for-a-delivery)
        * 5.3.8 [Delete Delivery](#delete-delivery)
    * 5.4 [Miscellaneous](#miscellaneous)
        * 5.4.1 [Help](#help)
        * 5.4.2 [Exit](#exit)
        * 5.4.3 [Clear](#clear)
* 6\. [FAQ](#faq)
* 7\. [Command Summary](#command-summary)

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

# Navigating the User Guide

**Note Box**
<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">
Provides you with information that is useful to know.
</box>

**Tip Box**
<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">
Provides you with information that can help enhance your user experience but is not necessary to know.
</box>

**Warning Box**
<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">
Provides you with important information to take note of to avoid any unintended effects!
</box>

**Links**

* Words in blue are [links](#navigating-the-user-guide) that you can click on to navigate to the relevant section.

* A [&uarr; Back to Table of Contents](#table-of-contents) link is available at the end of every section
  for you to return to the Table of Contents, so that you can access another section from there easily.

---

# Getting Started

## Prerequisites

Ensure that you have `Java 11`{.swift} or above installed on your computer. If you are unsure how to do so you may view
this
helpful [guide](https://www.java.com/en/download/help/version_manual.html).

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip**: You may find the following links helpful should you need to install `Java 11`{.swift}

1. [Java 11 Release](https://www.oracle.com/java/technologies/downloads/#java11).
2. [Java 11 Installation Guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
   .

</box>

## Installing HomeBoss

1. You may download the latest release of **HomeBoss**
   from [here](https://github.com/AY2324S1-CS2103T-T13-3/tp/releases/latest).
   (Please download the file named `HomeBoss.jar`{.swift})
2. Move the downloaded file `HomeBoss.jar`{.swift} into the folder that you want to use as the home folder for your
   application.

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning**: The folder that you would like to use as the home folder must be empty and should not contain any other
files/folder before the application is launched for the first time.

</box>

3. Run `HomeBoss.jar`{.swift}. If you are unsure how to run a `.jar`{.swift} file, you may refer to this helpful
   [guide](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Run-JAR-file-example-windows-linux-ubuntu)
   .
4. If everything went well, you should be greeted by a window similar to the one below.
5. We recommend reading how to [understand the layout](#understanding-the-layout)
   and familiarizing yourself with [HomeBoss's command format](#homebosss-command-format)
   before learning how to [add your first Delivery](#adding-your-first-delivery)

![HomeBoss Start Page](images/getting-started/register.png)

## Understanding our layout

Don't worry if you don't understand what you're looking at. Let's go through a quick run down of HomeBoss's layout
so that you can familiarise yourself with our various components.

![HomeBoss Home Page](images/getting-started/homepage_annotated.png)

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**: The above image is an example with sample data and may not be what you see when you launch
the application for the first time.

</box>

### Command Box

![HomeBoss Command Box](images/getting-started/command_box.png){style="display: block; margin: 0 auto;"}

The Command Box is where you will be interacting with HomeBoss, it is the place where all commands
will be entered. Try typing the `help`{.swift} command and hitting enter!

### Command Result/FeedBack

![Command Result FeedBack](images/getting-started/help_feedback.png){style="display: block; margin: 0 auto;"}

The Command Result/Feedback Box is where HomeBoss will provide you the results or feedback about
the commands you entered. For example, if you tried typing the `help`{.swift} command earlier, you should see
the above feedback in your application window, together with a Help window popup.
You can use the scroll bar on the right to scroll through a long result or feedback!

### Customer/Delivery List

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
        <td style="padding-top: 10px; padding-bottom: 20px">List of Customers</td>
        <td style="padding-top: 10px; padding-bottom: 20px">List of Deliveries</td>
    </tr>
</table>

The Customer/Delivery List Panel is where you will be able to find the Customers and Deliveries that you have added to
HomeBoss. You can use the scroll bar on the right to scroll through your Customers or Deliveries.

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**: The `ID`{.swift} that appears next to each name is a unique identifier given to all added Customers and
Deliveries separately, and is required to perform many of HomeBoss's commands.
More details of these commands can be found in [Features](#features)
  
![Command Result FeedBack](images/getting-started/delivery_card.png){style="display: block; margin: 0 auto;"}

</box>

## HomeBoss's Command Format

Commands are your main form of interaction with HomeBoss, and is the way to carry out various operations in HomeBoss.
Don't worry if the commands seem daunting, the HomeBoss team has carefully crafted the commands to be as intuitive
and simple as possible. After learning the commands, you'll be faster than ever!

Every command has a **Command Phrase** and possibly one or more **Parameters**
(Inputs to customise the command to your needs) which is typically preceded by a **Prefix**
(Special markers for HomeBoss to understand your inputs)

Take the [Add Delivery Command](#add-delivery) for example:

```swift
delivery add DELIVERY_NAME --customer CUSTOMER_ID --date DELIVERY_DATE
```

* The **Command Phrase** is `delivery add`{.swift}
* The **Parameters** are the words in `UPPER_CASE`{.swift}, for example `DELIVERY_NAME`{.swift} or `CUSTOMER_ID`{.swift}
* The **Prefixes** are special keywords that are preceded by `--`{.SWIFT}, such as `--customer`{.swift} and
  `--date`{.swift}

<br />

However not all commands have **Prefixes**, take the
[Update Delivery Status Command](#update-delivery-status) for example:

```swift
delivery status STATUS CUSTOMER_ID
```

* The **Command Phrase** is `delivery status`{.swift}
* The **Parameters** are `STATUS`{.swift} and `CUSTOMER_ID`{.swift}
* It has no **Prefixes**

<br />

Not all commands have **Parameters** as well, take the
[Logout Command](#logout) for example:

```swift
logout
```

* The **Command Phrase** is `logout`{.swift}
* It has no **Parameters**

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**:

* Command Phrase are keywords or short phrases that are unique to each Command.
* Words that are in `UPPER_CASE`{.swift} are parameters that are to be supplied by you.
* Parameters that are preceded by a prefix must be supplied after that prefix, e.g. `--name DELIVERY_NAME`{.swift}
  must be given as `--name deliveryName`{.swift} and not `deliveryName`{.swift} or `--name`{.swift}
* Items that are placed in square brackets (`[]`{.swift}) are optional, e.g. `DELIVERY_ID
  [--name DELIVERY_NAME]`{.swift} can either be `1`{.swift} or `1 --name deliveryName`{.swift}.
* However, items that are grouped together in square brackets (`[]`{.swift}) must all be provided together,
  e.g. for `[--password PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift} both `PASSWORD`{.swift} and
  `CONFIRM_PASSWORD`{.swift} must be provided.
* Extraneous parameters for commands that do not have parameters (e.g. `customer list`{.swift} or `clear`{.swift}) will
  be ignored, e.g. `customer list 123`{.swift}.
* Parameters that have `...`{.swift} as a postfix can be given one or more times, e.g. `KEYWORDS...`{.swift} can be
  given as `Query`{.swift} or `Query AnotherQuery`{.swift}.
* **Command Phrase** and **Prefixes** are **case-sensitive**! e.g. `clear`{.swift} is a valid command word but
  `customer LIST`{.swift} is not a valid command word.
* Leading and trailing whitespaces of parameters will be trimmed, i.e., spaces added between `--password`{.swift}  and `PASSWORD`{.swift}  in `--password PASSWORD`{.swift} will be removed.

</box>

## Registering and creating your first Delivery

Hopefully at this point, you have a good understanding of the basics of HomeBoss. Let's get you started on HomeBoss
by creating your first Delivery

1. First register for HomeBoss using the `register`{.swift} command. So, for example, if you want to register an account
   with the following details:

> `USERNAME`: Alex123
> `PASSWORD`: AlexIsGreat
> `CONFIRM_PASSWORD`: AlexIsGreat
> `SECRET_QUESTION`: First Pet Name? 
> `ANSWER`: KoKo 

Type `register --user Alex123 --password AlexIsGreat --confirmPass AlexIsGreat --secretQn First Pet Name? 
--answer Koko`{.swift} into the Command Box and hit enter. More details on the command can be found [here](#register).

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note**: Only 1 account can be registered to HomeBoss at any one time.

</box>

2. After registering for an account, you should be greeted by some sample Customers
   that have been generated by HomeBoss.

3. Let's assume that a new Customer, Gabriel, is to be added, using the `customer add`{.swift} command.

> `NAME`{.swift}: Gabriel
> `PHONE`{.swift}: 87654321
> `EMAIL`{.swift}: gabrielrocks@gmail.com
> `ADDRESS`{.swift}: RVRC Block B

4. Type `customer add --name Gabriel --phone 87654321 --email gabrielrocks@gmail.com --address RVRC Block B`{.swift}
   into the Command Box and hit enter.

5. A new Customer called Gabriel should be added, you should see a new Customer similar to the image below.

![CustomerAdded](images/getting-started/customer_add.png){style="display: block; margin: 0 auto;"}

6. Now, let's add a new Delivery for Gabriel into HomeBoss, suppose Gabriel ordered a MacBook Pro.
> `DELIVERY_NAME`{.swift}: MacBook Pro
> `DELIVERY_DATE`{.swift}: 2023-12-03

8. Type `delivery add MacBook Pro --customer CUSTOMER_ID --date 2023-12-03`{.swift}, replacing `CUSTOMER_ID`{.swift}
   with Gabriel's ID, and hit enter.

9. A new Delivery should have been added for Gabriel, you should see a new Delivery similar to the image below.

![DeliveryAdded](images/getting-started/delivery_add.png){style="display: block; margin: 0 auto;"}

Congratulations! You have just successfully added your first Customer and Delivery!

HomeBoss has many other features for you to try to streamline your Delivery workflow. Take a look at our
[Features](#features) below for even more commands and their details!

---

# Feature Summary

Here is a brief introduction to all the features in HomeBoss. For more detailed information, please
refer to the [Features](#features) section.

### User

_These are features for managing your user account in HomeBoss._

- `register`{.swift} - Registers a new user account to use HomeBoss.
- `login`{.swift} - Logs in to your user account.
- `update`{.swift} - Updates your user account details.
- `recover account`{.swift} - Recovers your user account.
- `logout`{.swift} - Logs out of your user account.
- `delete account`{.swift} - Deletes your user account.

### Customer

_These are features for managing your Customers in HomeBoss._

- `customer add`{.swift} - Adds a customer to the address book.
- `customer view`{.swift} - Shows the details of the specified Customer.
- `customer list`{.swift} - Lists all Customers in the address book.
- `customer find`{.swift} - Finds Customers whose names contain any of the given keywords.
- `customer edit`{.swift} - Updates the details of an existing Customer in the address book.
- `customer delete`{.swift} - Deletes the specified Customer from the address book.


### Delivery

_These are features for managing your Deliveries in HomeBoss._

- `delivery add`{.swift} - Adds a Delivery to the delivery book.
- `delivery view`{.swift} - Shows the details of the specified Delivery.
- `delivery list`{.swift} - Lists all Deliveries in the delivery book.
- `delivery find`{.swift} - Finds deliveries whose names contain any of the given keywords.
- `delivery edit`{.swift} - Updates the details of an existing Delivery in the Delivery book.
- `delivery status`{.swift} - Changes the status of a specified Delivery.
- `delivery note`{.swift} - Creates a note for a specified Delivery.
- `delivery delete`{.swift} - Deletes the specified delivery from the delivery book.

### Miscellaneous

_These are general features in HomeBoss._

- `exit`{.swift} - Exits the program.
- `help`{.swift} - Shows a list of commands and their usage.
- `clear`{.swift} - Clears both Customer and Delivery database. **Warning:** This action is irreversible.

---

# Features

\*[ ] specifies optional fields

## <span style="text-decoration:underline;">User</span>

### Register

Register an account before you start using HomeBoss.

_Register an account before you start using HomeBoss._

**Format:**

</br>`register --user USERNAME --password PASSWORD --confirmPass CONFIRM_PASSWORD --secretQn
SECRET_QUESTION --answer ANSWER`{.swift}


<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

</br>**Note:**

* `USERNAME`{.swift} must be alphanumeric.
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.
* `CONFIRM_PASSWORD`{.swift} must be the same as `PASSWORD`{.swift}.
* `SECRET_QUESTION`{.swift} can include any characters but must not be empty.
* `ANSWER`{.swift} can include any characters but must not be empty.
* `SECRET_QUESTION`{.swift} and `ANSWER`{.swift} will be used for account recovery.
  </box>

</br>**Example:**

* `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat
  --secretQn First Pet Name? --answer Koko`{.swift} </br>
  Registers a new user account with the username `Gabriel`{.swift}, password `GabrielIsGreat`{.swift}, secret
  question `First Pet Name?`{.swift} and answer `Koko`{.swift}.

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

</br>**Tip:**
Only one account can be registered at any one time. If you have already registered an account, you will not be able to register another account. If you must, you can delete your current account by calling `delete account`{.swift} [here](#delete-account) before registering a new account.
</box>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### Login

> Logs in to your user account.

_You can log in to your account to access your Customer and Delivery data by calling this command.
Note that you need to have an account registered with HomeBoss._

**Format:** `login --user USERNAME --password PASSWORD`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `USERNAME`{.swift} must be alphanumeric and can contain spaces.
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.
  </box>

**Example:**

* `login --user Gabriel --password GabrielIsGreat`{.swift} </br>
  Logs in to the user account with the username `Gabriel`{.swift} and password `GabrielIsGreat`{.swift}.

![](images/ug/login.png)
[&uarr; Back to Table of Contents](#table-of-contents)

### Update Account details

> Updates your user account details.

_You can update your account details to keep your account secure by calling this command._

**Format:** `update [--user USERNAME] [--password PASSWORD --confirmPass CONFIRM_PASSWORD]
[--secretQn SECRET_QUESTION --answer ANSWER]`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* At least one of the optional fields must be provided.
* The details will be updated without checking against the current details.
* `USERNAME`{.swift} must be alphanumeric.
* `PASSWORD`{.swift} must be at least 8 alphanumeric characters long.
* `CONFIRM_PASSWORD`{.swift} must be the same as `PASSWORD`{.swift}.
* `SECRET_QUESTION`{.swift} can take any characters.
* `ANSWER`{.swift} can take any characters.

  </box>

**Example:**

* `update --user GabrielV2 --password GabrielIsBest --confirmPass GabrielIsBest
  --secretQn Favourite Pet --answer BoBo`{.swift} </br>
  Updates the username to `GabrielV2`{.swift}, password to `GabrielIsBest`{.swift},
  secret question to `Favourite Pet`{.swift} and answer to `BoBo`{.swift}.

[&uarr; Back to Table of Contents](#table-of-contents)

### Logout

> Logs out of your user account.

_You can log out of your account to keep your data secure at the end of the day by calling this command._

**Format:** `logout`{.swift}

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### Recover Account

HomeBoss makes it easy for you to recover your account if you forget your password. You only need the answer to the
secret question that was set during account registration.

</br>**Format:** `recover account [--answer ANSWER --password NEW_PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

</br>**Note:**

* If `ANSWER`{.swift} is provided, `NEW_PASSWORD`{.swift} and `CONFIRM_PASSWORD`{.swift} must also be provided and vice
  versa.
* `ANSWER`{.swift} can take any characters.
* `NEW_PASSWORD`{.swift} must be at least 8 alphanumeric characters long.
* `CONFIRM_PASSWORD`{.swift} must be the same as `NEW_PASSWORD`{.swift}.
  </box>

</br>**Examples:**

* `recover account`{.swift} </br>
  Displays your account's secret question for account recovery.

* `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`{.swift} </br>
  Recovers the account with the answer `Koko`{.swift} and sets the new password to `NewPassword123`{.swift}.

</br>**Without optional fields (i.e., `recover account`{.swift} ):**
![](images/user/userRecoverAccount_after_secretQn.png)

</br>**With optional fields (i.e.,
`recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`{.swift}):**
![](images/user/userRecoverAccount_after_answer.png)

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

### Delete Account

If you simply want to erase all your data from HomeBoss, you can delete your account by calling this command.

</br>**Format:** `delete account`{.swift}

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

</br>**Tip:**

This command should be considered a last resort, to be used if you forget both your password and your secret answer.
However, if you've forgotten your password but recall the answer to your secret question, you can initiate the account
recovery process by clicking `recover account`{.swift} [here](#account-recovery).
</box>

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

## <span style="text-decoration:underline;">Customer</span>

### Add a Customer

> Adds a Customer to the address book.

_You can add a new Customer's details into HomeBoss with this command._

**Format:** `customer add --name NAME --phone PHONE_NUMBER --email EMAIL --address ADDRESS`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* HomeBoss does not allow you to add Customers with the same phone number.
* A unique customer ID (may not be consecutive) will be assigned to the Customer.
* `NAME`{.swift} must be alphanumeric and can contain spaces.
* `PHONE_NUMBER`{.swift} must be exactly 8 digits.
* `EMAIL`{.swift} must follow the `local@domain`{.swift} format.
* `ADDRESS`{.swift} can take any characters.
  </box>

**Example:**

* `customer add --name Gabriel --phone 87654321 --email Gabrielrocks@gmail.com --address RVRC Block B`{.swift} </br>
  Adds a Customer with the name `Gabriel`{.swift}, phone number `87654321`{.swift},
  email `Gabrielrocks@gmail.com`{.swift} and address `RVRC Block B`{.swift}.

![](images/ug/customer_add.png)

[&uarr; Back to Table of Contents](#table-of-contents)

### View details of a Customer

> Shows the details of the specified Customer.

If you want to see more details about a specific Customer, you can use the `customer view`{.swift} command to view a 
more in-depth description of the Customer.

**Format:** `customer view CUSTOMER_ID`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
  </box>

**Example:**

* `customer view 1`{.swift} </br>
  Displays the details of Customer with ID `1`{.swift}.

![](images/ug/customer_view.png)

[&uarr; Back to Table of Contents](#table-of-contents)

### List Customers

> Lists all the Customers added in the address book.

Want to get an overview of all your Customers in ONE list? Then, just use this feature to list all your Customers at
once.

**Format:** `customer list`{.swift}

<box background-color="#d9edf7" border-color="#bce8f1" type="tip" header="Tip">
    You can frequently use this command together with `delivery list` to switch between the two lists.
</box>

[&uarr; Back to Table of Contents](#top)

### Find Customers

> Finds Customers whose names have words that exactly match _ANY_ of the given keywords.

_If you want to find details of a Customer, but you do not remember the Customer's full name,
you can search for the Customer using keywords by calling this command._

**Format:** `customer find KEYWORD [MORE_KEYWORDS...]`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* You must provide at least one `KEYWORD`{.swift} to search for the Customer.
* You can optionally provide additional keywords, a Customer name that matches any of the given keywords will be 
  displayed.
  For example, the keywords `Alex Tan`{.swift} will display `Alex Wong`{.swift}, `Alex Tan`{.swift},
  and `Tan Ah Meng`{.swift}.
* The keyword must exactly match any word in the Customer's name. For example the keyword `Alex`{.swift} will match
  `Alex`{.swift} but not `Alexander`{.swift}.
* The search is not case sensitive.

  </box>

**Example:**

* `customer find Julius Yang`{.swift} </br>
  Finds Customers whose names have words that exactly match either `Julius`{.swift} or `Yang`{.swift}.

![](images/ug/find.png)

[&uarr; Back to Table of Contents](#table-of-contents)

### Update a Customer's details

> Updates the personal details of an existing customer in the address book.

Did you accidentally key in the wrong details for a Customer? Or perhaps your Customer has changed his particulars? No
worries! You can edit the details of a Customer simply by calling this command.

**Format:** `customer edit CUSTOMER_ID [--name NAME] [--phone PHONE_NUMBER] [--email EMAIL] [--address ADDRESS]`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* At least one of the optional fields must be provided. 
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
* `NAME`{.swift} must be alphanumeric and can contain spaces.
* `PHONE_NUMBER`{.swift} must be exactly 8 digits.
* `EMAIL`{.swift} must follow the `local@domain`{.swift} format.
* `ADDRESS`{.swift} can take any characters.

</box>

**Examples:**


- `customer edit 1 --name Gabriel --phone 9813 1051`{.swift} </br>
  Edits Customer 1's name to Gabriel and phone to 9813 1051.
- `customer edit 2 --name Joe --email yangyang@gmail.com --address Block 10 Tampines Road`{.swift} </br>
  Edits Customer 2's name to Joe, email to yangyang@gmail.com and address to Block 10 Tampines Road.\


  [&uarr; Back to Table of Contents](#top)
</br>

### Delete a Customer


> Deletes the specified Customer from the Customer database. All Deliveries associated with the Customer will also be
> deleted.

_You can delete any Customers who no longer require your services from HomeBoss by calling this command._

**Format:** `customer delete CUSTOMER_ID`{.swift}

<box background-color="#f2dede" border-color="#ebccd1" type="warning" icon=":exclamation:">

**Warning:**

Be careful! You won't be able to undo this edit action!
</box>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.

</box>

</br>**Example:**

* `customer delete 1`{.swift} </br>
  Deletes the Customer with ID 1.

[&uarr; Back to Table of Contents](#table-of-contents)
</br>

## <span style="text-decoration:underline;">Delivery</span>

### Add a Delivery

> Adds a Delivery to the delivery book.

One of HomeBoss' most essential features. Before tracking your Deliveries, you have to first add them into our 
application. With this feature, you can do just that by adding a Delivery to the delivery book.

<box background-color="#dff0d8" border-color="#d6e9c6" type="info" header="Note">
    Deliveries consist of a Delivery Name, a Customer ID, an Order Date, a Delivery Date, a Delivery Status and 
  an Address to ship to. </br> </br>
  You don't have to fill in Order Date, Delivery Status and Address. Instead they will be initialised with these values: 

- Order Date: Today's date
- Delivery Status: CREATED
- Address: Customer's Address

With this, it helps you speed up the process as much as possible!
</box>

<box background-color="#dff0d8" border-color="#d6e9c6" type="info" header="Note">
    Delivery Status can be one of CREATED, SHIPPED, COMPLETED, CANCELLED.
</box>

**Format:** `delivery add DELIVERY_NAME --customer CUSTOMER_ID --date EXPECTED_DELIVERY_DATE`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `DELIVERY_NAME`{.swift} must be alphanumeric and can contain spaces.
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
* `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format.

</box>

**Example:**

- `delivery add Chocolate Cake --customer 1 --date 2023-12-12`{.swift} </br>
  Adds a Delivery with Delivery Name Chocolate Cake, with the Customer who has Customer ID 1, with Delivery Date
  of 2023-12-12, Order Date will be today's date, Delivery Status will be CREATED and Address will be the Customer's
  Address. \

![](images/ug/delivery_add.png)

[&uarr; Back to Table of Contents](#top)

### View details of a Delivery

> Shows the details of the specified delivery.

If you want to see more details about a specific Delivery, you can use the `delivery view`{.swift} command to
view a more in-depth description of the Delivery.

**Format:** `delivery view DELIVERY_ID`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.
</box>

**Example:**

* `delivery view 1`{.swift} </br>
  Displays the details of Delivery with ID `1`{.swift}.

![](images/ug/delivery_view.png)

[&uarr; Back to Table of Contents](#table-of-contents)

### View a list of Deliveries

> Lists the Deliveries in the delivery book.

_If you would like to get an overview of all your Deliveries, you can use `delivery list`{.swift} to list all your
Deliveries at once. You can also filter the list of Deliveries by status, customer ID and expected delivery date.
You can also sort the list of Deliveries by expected delivery date in ascending or descending order. By default, the
list of Deliveries will be sorted by expected delivery date in descending order (latest first)._

**Format:**
`delivery list [--status STATUS] [--customer CUSTOMER_ID] [--date EXPECTED_DELIVERY_DATE]  [--sort SORT]`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `STATUS`{.swift} accepts the following values: `CREATED`{.swift}/`SHIPPED`{.swift}/`COMPLETED`{.swift}/
  `CANCELLED`{.swift}.
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
* `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format OR
  `TODAY`{.swift} for today’s date.
* `SORT`{.swift} accepts the following values: `ASC`{.swift}/`DESC`{.swift}.
  </box>

**Examples:**

* `delivery list --status CREATED --customer 1 --sort DESC`{.swift} </br>
  Lists all Deliveries with status `CREATED`{.swift} for Customer with ID `1`{.swift} with expected delivery date in
  descending order.

* `delivery list --status SHIPPED --date TODAY`{.swift} </br>
  Lists all Deliveries with status `SHIPPED`{.swift} for all Customers and expected delivery date of today.

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:**
You may combine any of the filters and sort options to get the list of Deliveries that you want.
</box>

![](images/ug/delivery_list.png)

[&uarr; Back to Table of Contents](#top)

### Find Deliveries

> Finds Deliveries whose names has words that exactly match _ANY_ of the given keywords.

_Want to find details of a Delivery, but you do not remember the Delivery's full name?
You can search for the Delivery using keywords by calling this command._

**Format:** `delivery find KEYWORD [MORE_KEYWORDS...]`{.swift}

<box background-color="#dff0d8" border-color="#d6e9c6" type="info" header="Note">

* You must provide at least one alphanumeric `KEYWORD`{.swift} to search for the Delivery.
* You can optionally provide additional keywords, a Delivery that matches any of the given keywords will be displayed.
  For example, the keywords `Chocolate Bun`{.swift} will display `Chocolate Cake`{.swift}, `Chocolate Bun`{.swift},
  and `Strawberry Cake`{.swift}.
* The keyword must exactly match any word in the Delivery name. For example the keyword `Straw`{.swift} will match
  `Straw`{.swift} but not `Strawberry`{.swift}.
* The search is not case sensitive.

</box>

**Example:**
`delivery find Gambes Banana`{.swift} </br>
Find all Deliveries whose name has words that exactly match `Gambes`{.swift} or `Banana`{.swift}

![](images/ug/delivery_find.png)

[&uarr; Back to Table of Contents](#table-of-contents)

#### Update a Delivery's details

> Updates the Delivery details of an existing Delivery in the delivery book.

Oh no! Did you key in the wrong details for a Delivery? Or did your Customer change some details to his
Delivery? No worries! Just edit the details of a Delivery simply by using this feature.

**Format:** `delivery edit DELIVERY_ID [--name DELIVERY_NAME] [--customer CUSTOMER_ID] [--date EXPECTED_DELIVERY_DATE]
[--status STATUS] [--note NOTE]`{.swift}

<box background-color="#f2dede" border-color="#ebccd1" type="warning" header="Warning">
    Be careful! You won't be able to undo this edit action! 
</box>

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* At least one of the optional fields must be provided.
* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.
* `DELIVERY_NAME`{.swift} must be alphanumeric and can contain spaces.
* `CUSTOMER_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Customer.
  `EXPECTED_DELIVERY_DATE`{.swift} must be today or after today's date in yyyy-MM-dd format.

  </box>

<box background-color="#d9edf7" border-color="#bce8f1" type="tip" header="Tip">
  
    If you only want to update the Delivery's status, simply use `delivery status`{.swift} instead.
  
</box>

**Examples:**

- `delivery edit 1 --status CANCELLED --note Sudden overseas business trip to attend to.`{.swift} </br>
  Edits Delivery Status of the Delivery, with Delivery ID of 1, to CANCELLED and edits the note of the Delivery.
  (If Delivery does not have a note, a note will be created to the Delivery)
- `delivery edit 2 --name Vanilla Cake --customer 3`{.swift} </br>
  Edits Delivery with the ID of 2's Delivery Name to Vanilla Cake as it was misspelled and edits the Customer to 
  Customer 3 as User accidentally typed in 2 instead. \

  [&uarr; Back to Table of Contents](#top)

### Update a Delivery's status

> Changes the status of a specified delivery to CREATED, SHIPPED, COMPLETED or CANCELLED

I see that you've just shipped out an order. Nice! Now you can update the status of that particular Delivery
to SHIPPED! Using this feature, you can update the status of a Delivery accordingly.


<box background-color="#d9edf7" border-color="#bce8f1" type="tip" header="Tip">
    Delivery Status typed in need not be in uppercase.
</box>

**Format:** `delivery status DELIVERY_ID STATUS`{.swift}


<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.
* `STATUS`{.swift} accepts the following values: `CREATED`{.swift}/`SHIPPED`{.swift}/`COMPLETED`{.swift}/
  `CANCELLED`{.swift}.

  </box>

**Examples:**

- `delivery status 1 CANCELLED`{.swift}
- `delivery status 2 SHIPPED`{.swift}

[&uarr; Back to Table of Contents](#top)

### Create a note for a Delivery

> Creates a note for a specified Delivery

If you would like create a note about a specific Delivery, you can use `delivery note`{.swift} to add a quick
note to a specific Delivery.

**Format:** `delivery note DELIVERY_ID --note NOTE`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* If the Delivery already has a Note it will be overwritten by the new Note given.
* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.
* `NOTE`{.swift} must be alphanumeric and can contain spaces.
  </box>

**Example:**

* `delivery note 1 --note By FedEx`{.swift} </br>
  Creates a new Note "By FedEx" for the Delivery with ID `1`{.swift}

[&uarr; Back to Table of Contents](#top)

### Delete a Delivery

> Deletes the specified Delivery.

Feel like your delivery book is getting cluttered up? Maybe you just want to get rid of a few Deliveries that have
been cancelled or completed. If that's the case, simply use this feature to delete the Delivery.

**Format:** `delivery delete DELIVERY_ID`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* `DELIVERY_ID`{.swift} must be an integer greater than 0 that corresponds to an existing Delivery.

</box>

<box background-color="#f2dede" border-color="#ebccd1" type="warning" header="Warning">
    Be careful! This action is irreversible. Once deleted, the Delivery cannot be recovered.
</box>

**Example:**

- `delivery delete 1`{.swift} </br>
  Deletes Delivery 1 from the delivery book.

  [&uarr; Back to Table of Contents](#top)

_Details coming soon..._

## <span style="text-decoration:underline;">Miscellaneous</span>

### Help

> Shows a list of commands and their usage.

_You can view a summary of the commands available, the format of the commands, and a link to this user guide
by calling this command._

**Format:** `help`{.swift}

<box type="tip" background-color="#d9edf7" border-color="#bce8f1" icon=":bulb:">

**Tip:**

* You can access this command anytime when using the application, even when you are not logged in.
  </box>

![](images/ug/help.png)

[&uarr; Back to Table of Contents](#table-of-contents)

### Exit

> Exits the program.

_You can exit the program by calling this command._

**Format:** `exit`{.swift}

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:**

* If you are logged in, this command will automatically log you out of your account and close the application.
  </box>

[&uarr; Back to Table of Contents](#table-of-contents)

### Clear

> Clears both customer and delivery database.

_Need to clear your Customer and Delivery data?
You can delete all your Customer and Delivery data by calling this command._

**Format:** `clear`{.swift}

<box type="warning" background-color="#f2dede" border-color="#ebccd1" icon=":exclamation:">

**Warning:**

* Be careful, this action is irreversible! All your Customer and Delivery data will be deleted permanently.
  Proceed with caution!
  </box>



[&uarr; Back to Table of Contents](#table-of-contents)

# FAQ

**Q: Where do I get support for HomeBoss?**

A: You can raise an issue on our [GitHub repository](https://github.com/AY2324S1-CS2103T-T13-3/tp/issues)

**Q: How do I report a bug?**

A: You can raise a bug report on our [GitHub repository](https://github.com/AY2324S1-CS2103T-T13-3/tp/issues)

**Q: How do I import my data from another software?**

A: You can import your data by converting your data into .json in the format of the Customer and Delivery data in the
data folder.

**Q: How do I export my data to another software?**

A: It is currently not possible to export your data to another software.

**Q: Why is there an error when I input non-English characters?**

A: HomeBoss only supports English characters.

**Q: What happens if the json file (file where the Customer/Delivery data is kept) is edited manually and invalid 
data is being written to the file?**

A: HomeBoss does not recommend the editing of files manually. However, if the files are edited and invalid data is 
input, HomeBoss will discard the entire data file and start with an empty data file at the next run. 
(If the Customer data file has invalid data, the whole Delivery data file will be deleted as 
well. However, if the Delivery data file  has invalid data, the Customer data file will not be affected.)

# Command Summary

#### User

| Command  | Format                                                                                                                       | Examples                                                                                                                    |
|----------|------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Register | `register --user USERNAME --password PASSWORD --confirmPass CONFIRM_PASSWORD --secretQn SECRET_QUESTION --answer ANSWER`{.swift}     | `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name? --answer Koko`{.swift} |
| Login    | `login --user USERNAME --password PASSWORD`                                                                                  | `login --user gabriel --password gabrielIsGreat`                                                                            |
| Update   | `update [--user USERNAME] [--password PASSWORD --confirmPass CONFIRM_PASSWORD] [--secretQn SECRET_QUESTION --answer ANSWER]` | `update --user gabrielV2 --password gabrielIsBest --confirmPass gabrielIsBest --secretQn Favourite Pet --answer BoBo`       |
| Recover  | `recover account [--answer ANSWER --password NEW_PASSWORD --confirmPass CONFIRM_PASSWORD]`{.swift}                                   | `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`{.swift}                                      |
| Logout   | `logout`                                                                                                                     | `logout`                                                                                                                    |
| Delete   | `delete account`{.swift}                                                                                                             | `delete account`{.swift}                                                                                                            |

#### Customer

| Command | Format                                                                                               | Examples                                                                                                 |
|---------|------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| Add     | `customer add --name NAME --phone PHONE_NUMBER --email EMAIL --address ADDRESS`                      | `customer add --name Gabriel --phone 87654321 --email gabrielrocks@gmail.com --address RVRC Block B`     |
| View    | `customer view CUSTOMER_ID`                                                                          | `customer view 1`                                                                                        |
| List    | `customer list`                                                                                      | `customer list`                                                                                          |
| Find    | `customer find KEYWORD [MORE_KEYWORDS]`                                                              | `customer find Ibrahim Yu`                                                                               |
| Edit    | `customer edit CUSTOMER_ID [--name NAME] [--phone PHONE_NUMBER] [--email EMAIL] [--address ADDRESS]` | `customer edit 1 --name Gabriel --phone 1234 5678 --email gabrielrocks@gmail.com --address RVRC Block B` |
| Delete  | `customer delete CUSTOMER_ID`                                                                        | `customer delete 1`                                                                                      |


#### Delivery

| Command | Format                                                                                                                             | Examples                                                                                                                |
|---------|------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| Add     | `delivery add DELIVERY_NAME --customer CUSTOMER_ID --date DELIVERY_DATE`                                                           | `delivery add furniture --customer 5 --date 2023-12-03`                                                                 |
| View    | `delivery view DELIVERY_ID`                                                                                                        | `delivery view 1`                                                                                                       |
| List    | `delivery list [--status STATUS] [--customer CUSTOMER_ID] [--date DELIVERY_DATE] [--sort SORT]`                                    | `delivery list --status created --customer 1 --date 2023-12-12 --sort desc`                                             |
| Find    | `delivery find KEYWORD [MORE_KEYWORDS]`                                                                                            | `delivery find Ibrahim Yu`                                                                                              |
| Edit    | `delivery edit DELIVERY_ID [--name DELIVERY_NAME] [--customer CUSTOMER_ID] [--date DELIVERY_DATE] [--status STATUS] [--note NOTE]` | `delivery edit 1 --name Chocolate Cake --customer 2 --date 2024-12-12 --status CANCELLED --note Customer changed his mind` |
| Status  | `delivery status DELIVERY_ID STATUS`                                                                                               | `delivery status 2 completed`                                                                                           |
| Note    | `delivery note DELIVERY_ID --note NOTE`                                                                                            | `delivery note 1 --note By FedEx`                                                                                       |
| Delete  | `delivery delete DELIVERY_ID`                                                                                                      | `delivery delete 1`                                                                                                     |

#### Other

| Command | Format  | Examples |
|---------|---------|----------|
| Exit    | `exit`  | `exit`   |
| Help    | `help`  | `help`   |
| Clear   | `clear` | `clear`  |

