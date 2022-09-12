package com.rebirth.mycode.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "snippet_code", uniqueConstraints = {
        @UniqueConstraint(name = "uq_webid", columnNames = {"code_webid"})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SnippetCode extends Auditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id", nullable = false)
    private Long id;

    @Column(name = "code_webid", nullable = false)
    private UUID uuid;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "rating", nullable = false)
    private float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    @ToString.Exclude
    private Language language;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "snippetcode_tag",
            joinColumns = @JoinColumn(name = "code_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    private List<Tag> tags;

    public String getLanguageName() {
        return this.language.getName();
    }

    public List<String> getTagsName() {
        return this.tags.stream().map(Tag::getName).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SnippetCode that = (SnippetCode) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
