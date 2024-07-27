package service.impl;

import models.BookStore;
import org.springframework.stereotype.Service;
import repository.StoreRepository;
import service.StoreService;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public BookStore addStore(BookStore bookStore) {
        bookStore.setId(null);
        return storeRepository.save(bookStore);
    }

    @Override
    public BookStore updateStoreData(BookStore bookStore, Long id) {
        Optional<BookStore> bookStoreOptional = storeRepository.findById(id);
        if (bookStoreOptional.isPresent()) {
            BookStore existingBookStore = bookStoreOptional.get();
            existingBookStore.setName((bookStore.getName() != null) ? bookStore.getName() : existingBookStore.getName());
            existingBookStore.setAddress((bookStore.getAddress() != null) ? bookStore.getAddress() : existingBookStore.getAddress());
            return storeRepository.save(existingBookStore);
        }
        throw new IllegalArgumentException("Store not found");
    }

    @Override
    public BookStore getStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Store not found"));
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
