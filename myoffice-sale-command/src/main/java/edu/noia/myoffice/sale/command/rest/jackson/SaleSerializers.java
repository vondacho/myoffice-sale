package edu.noia.myoffice.sale.command.rest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonComponent
public class SaleSerializers {

    public static class FolderIdSerializer extends JsonSerializer<FolderId> {
        @Override
        public void serialize(FolderId folderId, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (folderId != null) {
                gen.writeString(folderId.getId().toString());
            }
        }
    }

    public static class FolderIdDeserializer extends JsonDeserializer<FolderId> {
        @Override
        public FolderId deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(parser.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(UUID::fromString)
                    .map(FolderId::of)
                    .orElse(null);
        }
    }

    public static class ArticleIdSerializer extends JsonSerializer<ArticleId> {
        @Override
        public void serialize(ArticleId articleId, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (articleId != null) {
                gen.writeString(articleId.getId().toString());
            }
        }
    }

    public static class ArticleIdDeserializer extends JsonDeserializer<ArticleId> {
        @Override
        public ArticleId deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
            return Optional.ofNullable(parser.readValueAs(String.class))
                    .filter(StringUtils::hasText)
                    .map(UUID::fromString)
                    .map(ArticleId::of)
                    .orElse(null);
        }
    }
}
