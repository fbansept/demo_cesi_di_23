package edu.cesi.demo.controller;

import edu.cesi.demo.dao.PurchaseOrderDao;
import edu.cesi.demo.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @GetMapping("/purchase-order/{id}")
    public PurchaseOrder getPurchaseOrder(@PathVariable int id){
        return purchaseOrderDao.findById(id).orElse(null);
    }

    @GetMapping("/purchase-orders")
    public List<PurchaseOrder> getPurchaseOrders() {
        List<PurchaseOrder> listPurchaseOrder = purchaseOrderDao.findAll();
        return listPurchaseOrder;
    }

    @DeleteMapping("/purchase-order/{id}")
    public void deletePurchaseOrder(@PathVariable int id){
        purchaseOrderDao.deleteById(id);
    }

    @PostMapping("/purchaseOrder")
    public void editPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrderDao.save(purchaseOrder);
    }
}
