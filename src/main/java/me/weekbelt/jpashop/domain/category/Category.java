package me.weekbelt.jpashop.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.jpashop.domain.Item.Item;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    private void setParent(Category parent) {
        this.parent = parent;
    }

    // Category Parent와 Child의 연관관계 메소드
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }


}
