package serenezz.contactapi.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static serenezz.contactapi.constant.Constant.PHOTO_DIRECTORY;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Page<Contact>> allContacts(
            @RequestParam (value = "page", defaultValue = "0") int page,
            @RequestParam (value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> singleContact(@PathVariable String id) {
        return ResponseEntity.ok().body(contactService.getSingleContact(id));
    }

    @PostMapping
    public ResponseEntity<Contact> addContact( @RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        URI location = URI.create("/contacts/" + createdContact.getId()); // Assuming getId() gives the ID of the newly created contact
        return ResponseEntity.created(location).body(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable String id, @RequestBody Contact contact) {
        return ResponseEntity.ok().body(contactService.updateContact(id, contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("id") String id,
            @RequestParam("file") MultipartFile img) {
        return ResponseEntity.ok().body(contactService.uploadPhoto(id, img));
    }


    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}
