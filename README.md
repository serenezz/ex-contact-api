# Contact API Backend Application

This repository contains a simple Spring backend application for managing a contact list, featuring basic CRUD operations and photo upload functionalities.

## Features

- **List all contacts**: Retrieve a paginated list of contacts.
- **Retrieve single contact**: Get details of a specific contact by ID.
- **Add new contact**: Create a new contact entry.
- **Update contact**: Modify details of an existing contact.
- **Delete contact**: Remove a contact from the list.
- **Upload contact photo**: Upload a photo for a specific contact.
- **Retrieve contact photo**: Fetch a contact's photo by filename.

## Endpoints

- **GET /contacts**:
  Retrieves all contacts in a paginated format.

  Parameters:
    - `page` (optional, default 0): Page number.
    - `size` (optional, default 10): Number of contacts per page.

- **GET /contacts/{id}**:
  Retrieves details of a specific contact by ID.

- **POST /contacts**:
  Creates a new contact.

  Request Body: JSON representation of Contact object.

- **PUT /contacts/{id}**:
  Updates details of an existing contact by ID.

  Request Body: JSON representation of updated Contact object.

- **DELETE /contacts/{id}**:
  Deletes a contact by ID.

- **PUT /contacts/photo**:
  Uploads a photo for a specific contact.

  Parameters:
    - `id`: ID of the contact.
    - `file`: Image file to upload as multipart/form-data.

- **GET /contacts/image/{filename}**:
  Retrieves the photo of a contact by filename.