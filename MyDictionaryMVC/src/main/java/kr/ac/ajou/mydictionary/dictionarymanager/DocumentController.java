/**
 * @project MyDictionaryMVC
 * @package kr.ac.ajou.mydictionary.dictionarymanager
 * @file DocumentController.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:06:00
 *
 */
package kr.ac.ajou.mydictionary.dictionarymanager;

import java.util.Date;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.dictionarymanager.model.DocumentJSONModel;
import kr.ac.ajou.mydictionary.document.DocumentModel;
import kr.ac.ajou.mydictionary.document.DocumentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @package kr.ac.ajou.mydictionary.dictionarymanager
 * @type DocumentController.java
 * @author universe
 * @date 2016. 4. 24. 오후 11:06:00
 * @description
 * 
 */
@Controller
@RequestMapping(value = "/document")
public class DocumentController {
	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

	@Resource(name = "documentService")
	private DocumentService documentService;

	@RequestMapping(value = "/setDocument", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public @ResponseBody String setDocument(@RequestBody DocumentJSONModel documentJSONModel) {
		logger.info("[/setDocument]" + " - " + "Get in setDocument method");

		if (documentService.setDocument(new DocumentModel(documentJSONModel.getUserId(),
				documentJSONModel.getKeyword(), new Date(), documentJSONModel.getDocument()))) {
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "/deleteDocument", method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public @ResponseBody String deleteDocument(@RequestBody DocumentModel documentModel) {
		logger.info("[/deleteDocument]" + " - " + "Get in deleteDocument method");

		if (documentService.deleteDocument(documentModel)) {
			return "success";
		} else {
			return "fail";
		}
	}
}
